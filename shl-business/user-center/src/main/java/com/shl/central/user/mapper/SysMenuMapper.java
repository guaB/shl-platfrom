package com.shl.central.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.common.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    @Select("<script>" +
            "select distinct t.* from sys_menu t" +
            "join sys_role_menu r on r.menu_id = t.id" +
            "where in " +
            "<foreach collection = \"roleIds\" item = \"roleId\" index = \"index\" separator = \",\">" +
            "(roleId)" +
            "</foreach>" +
            "<if test = 'type != null'> and t.type = #{type} </if>" +
            " ORDER BY sort ASC " +
            "</script>")
    List<SysMenu> findByRoleCodes(@Param("roleIds") Set<String> roleCodes, @Param("type") Integer type);
}
