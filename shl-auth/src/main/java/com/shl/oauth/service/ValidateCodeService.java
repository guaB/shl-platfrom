package com.shl.oauth.service;

import com.shl.common.model.Result;
import com.shl.oauth.exception.ValidateCodeException;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 验证码service
 * @author: songhonglei
 * @date: 2019-12-19
 */
public interface ValidateCodeService  {
    /**
     * 保存图形验证码
     * @param deviceId 前端唯一标识
     * @param imageCode 验证码
     */
    void  saveImageCode(String deviceId, String imageCode);

    /**
     * 发送手机验证码
     * @param mobile 手机号
     * @return
     */
    Result sendSmsCode(String mobile);

    /**
     * 获取验证码
     * @param deviceId 前端唯一标识/手机号
     * @param type 1.图片验证  2.sms验证
     * @return
     */
    String getCode(String deviceId, Integer type);

    /**
     * 删除验证码
     * @param deviceId 前端唯一标识/手机号
     * @param type 1.图片验证  2.sms验证
     */
    void removeCode(String deviceId, Integer type);

    /**
     * 验证图片验证码
     * @param request 验证http请求
     * @throws ValidateCodeException 验证异常
     */
    void validateImageCode(HttpServletRequest request) throws ValidateCodeException;

    /**
     * 验证手机验证码
     * @param request 验证http请求
     * @throws ValidateCodeException 验证异常
     */
    void validateSmsCode(HttpServletRequest request) throws ValidateCodeException;

}
