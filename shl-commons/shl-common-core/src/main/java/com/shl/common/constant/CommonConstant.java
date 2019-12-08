package com.shl.common.constant;

/**
 * @author songhonglei
 * @version 1.0
 * @description 公共常量
 * @date 2019/12/8
 */
public enum CommonConstant {
    /**
     * token请求头名称
     */
    TOKEN_HEADER("Authorization"),

    /**
     * 客户端携带参数
     */
    BASIC("Basic "),
    /**
     * 超级管理员用户名
     */
    ADMIN("admin")
    ;
    private String msg;

    CommonConstant(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
