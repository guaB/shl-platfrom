package com.shl.central.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/11
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_user")
public class SysRoleUser extends Model<SysRoleUser> {
    private Long userId;
    private Long roleId;
}