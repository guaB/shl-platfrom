package com.shl.common.model;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-04
 */
public enum  CodeEnum {
    /**
     * success code
     */
    SUCCESS(0),
    ERROR(1);

    private Integer code;
    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
