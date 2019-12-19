package com.shl.oauth.service.impl;

import com.shl.common.constant.CommonConstant;
import com.shl.common.constant.SecurityConstants;
import com.shl.common.feign.UserService;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.Result;
import com.shl.common.model.SysUser;
import com.shl.oauth.exception.ValidateCodeException;
import com.shl.oauth.service.ValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-19
 */
@Slf4j
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserService userService;

    @Override
    public void saveImageCode(String deviceId, String imageCode) {
        redisTemplate.opsForValue().set(SecurityConstants.IMAGE_CODE_KEY + deviceId, imageCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public Result sendSmsCode(String mobile) {
        Object tempCode = redisTemplate.opsForValue().get(SecurityConstants.SMS_CODE_KEY + mobile);
        if(tempCode != null){
            log.error("用户:{}验证码未失效{}", mobile, tempCode);
            return Result.failed("验证码未失效，请失效后再次申请");
        }
        SysUser user = userService.findByMobile(mobile);
        if (user == null){
            log.error("根据用户手机号{}查询用户为空", mobile);
            return Result.failed("手机号不存在");
        }
        String code = RandomStringUtils.randomNumeric(4);
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, code);
        redisTemplate.opsForValue().set(SecurityConstants.SMS_CODE_KEY + mobile, code, 60, TimeUnit.SECONDS);
        return Result.succeed("true");
    }

    @Override
    public String getCode(String deviceId, Integer type) {
        return (String) redisTemplate.opsForValue().get(buildKey(deviceId, type));
    }

    @Override
    public void removeCode(String deviceId, Integer type) {
        redisTemplate.delete(buildKey(deviceId, type));
    }

    @Override
    public void validateImageCode(HttpServletRequest request) throws ValidateCodeException {
        String deviceId = request.getParameter("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求参数中携带deviceId参数");
        }
        String code = this.getCode(deviceId, CommonConstant.ONE_NUM);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, "validCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("请填写验证码");
        }
        if (code == null) {
            throw new ValidateCodeException("验证码不存在或已过期");
        }
        if (!StringUtils.equals(code, codeInRequest.toLowerCase())) {
            throw new ValidateCodeException("验证码不正确");
        }
        this.removeCode(deviceId, CommonConstant.ONE_NUM);
    }

    @Override
    public void validateSmsCode(HttpServletRequest request) throws ValidateCodeException {
        String moblie = request.getParameter("moblie");
    }

    private String buildKey(String deviceId, int type) {
        if(type == CommonConstant.ONE_NUM){
            return SecurityConstants.IMAGE_CODE_KEY + deviceId;
        }else {
            return SecurityConstants.SMS_CODE_KEY + deviceId;
        }
    }

}
