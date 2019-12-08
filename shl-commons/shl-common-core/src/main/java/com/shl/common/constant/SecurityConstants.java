package com.shl.common.constant;

/**
 * @author songhonglei
 * @version 1.0
 * @description Security配置
 * @date 2019/12/3
 */
public enum  SecurityConstants {
    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    CACHE_CLIENT_KEY("oauth_client_details"),
    /**
     * RAS 公钥
     */
    RSA_PUBLIC_KEY("pubkey.txt"),
    /**
     * 用户信息头
     */
    USER_HEADER("x-user-header"),
    /**
     * 用户id信息头
     */
    USER_ID_HEADER("x-userid-header"),
    /**
     * 角色信息头
     */
    ROLE_HEADER("x-role-header"),
    /**
     * 租户信息头(应用)
     */
    TENANT_HEADER("x-tenant-header"),
    ;

    private String msg;

    SecurityConstants(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
