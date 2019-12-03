package com.shl.oauth2.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-03
 */
@Data
public class SysUser extends SuperEntity{

    private static final long serialVersionUID = -5886012896705137070L;

    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String mobile;
    private Integer sex;
    private Boolean enabled;
    private String type;
    private String openId;
    @TableLogic
    private boolean isDel;

    @TableField(exist = false)
    private List<SysRole> roles;
    @TableField(exist = false)
    private String roleId;
    @TableField(exist = false)
    private String oldPassword;
    @TableField(exist = false)
    private String newPassword;
}
