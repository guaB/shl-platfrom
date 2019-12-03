package com.shl.oauth2.model;

import lombok.Data;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-03
 */
@Data
public class SysRole extends SuperEntity{
    private static final long serialVersionUID = 4497149010220586111L;
    private String code;
    private String name;
    private Long userId;
}
