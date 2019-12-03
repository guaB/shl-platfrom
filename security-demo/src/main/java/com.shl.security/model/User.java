package com.shl.security.model;
import	java.security.Identity;
import	java.util.jar.Attributes.Name;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("shl_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_name")
    private String userName;
    @TableField("user_no")
    private Integer userNo;
    @TableField("user_class")
    private Integer userClass;
}
