package com.shl.common.constant;

/**
 * @author songhonglei
 * @version 1.0
 * @description 公共常量
 * @date 2019/12/8
 */
public interface CommonConstant {
    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 客户端携带参数
     */
    String BASIC = "Basic ";
    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME  = "admin";

    /**
     * 目录
     */
    Integer CATALOG = -1;

    /**
     * 菜单
     */
    Integer MENU = 1;

    /**
     * 权限
     */
    Integer PERMISSION = 2;
    /**
     * 日志链路追踪id信息头
     */
    String TRACE_ID_HEADER = "x-traceId-header";
    /**
     * 日志链路追踪id日志标志
     */
    String LOG_TRACE_ID = "traceId";

    /**
     * 请求方法
     */
    String POST_METHOD = "POST";

    /**
     * 常量
     */
    Integer ONE_NUM = 1;

    /**
     * 常量
     */
    Integer TWO_NUM = 2;


}
