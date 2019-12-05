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

    TENANT_HEADER(""),
    ;
    private String msg;

    SecurityConstants(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
