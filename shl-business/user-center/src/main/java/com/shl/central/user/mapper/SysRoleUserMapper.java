package com.shl.central.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.central.user.model.SysRoleUser;
import com.shl.common.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    /**
     * @param userId
     * @return
     */
    @Select("select r.* from sys_role r " +
            "inner join sys_role_user ru on r.id = ru.role_id and ru.user_id = #{userId} ")
    List<SysRole> findRolesByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ids 获取
     *
     * @param userIds
     * @return
     */
    @Select("<script>select r.*,ru.user_id from sys_role_user ru inner join sys_role r on r.id = ru.role_id where ru.user_id IN " +
            " <foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
            " #{item} " +
            " </foreach>" +
            "</script>")
    List<SysRole> findRolesByUserIds(List<Long> userIds);
}
