package com.shl.central.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.central.user.model.SysRoleMenu;
import com.shl.common.model.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/11
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据roleCode  获取menu
     * @param roleCodes
     * @param type
     * @return
     */
    @Select("<script>" +
            "select distinct t.* from sys_menu t " +
            "inner join sys_role_menu r on r.menu_id = t.id " +
            "join sys_role rl on rl.id = r.id " +
            "where rl.code  in " +
            "<foreach collection = 'roleCodes' item = 'roleCode' index = 'index' separator = ','>" +
                "(#{roleCode})" +
            "</foreach>" +
            "<if test = 'type != null'> and t.type = #{type} </if>" +
            " and t.hidden = 0  ORDER BY t.sort ASC " +
            "</script>")
    List<SysMenu> findMenusByRoleCodes(@Param("roleCodes") Set<String> roleCodes, @Param("type") Integer type);


    /**
     * 根据角色roleId 获取menu
     * @param roleIds
     * @param type
     * @return
     */
    @Select("<script>" +
                "select distinct t.*  from sys_menu t inner join sys_role_menu r on r.menu_id = t.id where r.role_id in " +
                "<foreach collection = 'roleIds' item = 'roleId' index = 'index' open = '(' separator= ',' close = ')' >" +
                    "#{roleId}" +
                "</foreach>" +
                "<if test = 'type != null'> and t.type = #{type} </if>" +
                " ORDER BY t.sort ASC" +
            "</script>")
    List<SysMenu> findMenuByRoleIds(@Param("roleIds") Set<Long> roleIds, @Param("type") Integer type);
}
