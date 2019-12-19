package com.shl.common.constant;

/**
 * @author songhonglei
 * @version 1.0
 * @description Security配置
 * @date 2019/12/3
 */
public interface SecurityConstants {
    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    String CACHE_CLIENT_KEY = "oauth_client_details";

    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";
    /**
     * RAS 公钥
     */
    String RSA_PUBLIC_KEY = "pubkey.txt";
    /**
     * 租户信息头(应用)
     */
    String TENANT_HEADER = "x-tenant-header";
    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";
    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-userid-header";
    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/validate/code";

    /**
     * 手机号的处理验证码的url前缀
     */
    String MOBILE_VALIDATE_CODE_URL_PREFIX = "/validate/smsCode";

    /**
     * 图片code的前缀
     */
    String IMAGE_CODE_KEY = "IMAGE_CODE_KEY:";

    /**
     * SmsCode的前缀
     */
    String SMS_CODE_KEY = "SMS_CODE_KEY:";

    /**
     * PASSWORD模式登录处理地址
     */
    String PASSWORD_LOGIN_PRO_URL = "/oauth/user/token";

    /**
     * 默认的OPENID登录请求处理url
     */
    String OPENID_TOKEN_URL = "/oauth/openId/token";

    /**
     * 手机密码登录URL
     */
    String MOBILE_TOKEN_URL = "/oauth/mobile/token";

    /**
     * 手机短信登陆URL
     */
    String SMS_TOKEN_URL = "/oauth/sms/token";
}
