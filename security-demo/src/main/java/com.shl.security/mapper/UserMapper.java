package com.shl.security.mapper;

import com.shl.security.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
@Mapper
public interface UserMapper extends IBaseMapper<User> {

    @Select("select * from shl_user where user_class = #{userClass}")
    List<User> getUsers(@Param("userClass") Integer userClass);
}
