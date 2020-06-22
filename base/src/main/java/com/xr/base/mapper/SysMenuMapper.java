package com.xr.base.mapper;

import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper {
    //查询所有角色
    @Select({"<script>"
            , "SELECT * from sys_role" ,
            " where  1=1" ,
            "<when test = 'name!=null'>" ,
            "and `name` like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{limit} ",
            "</script>"})
    List<SysRole> roleList(@Param("name") String name,@Param("page") Integer page,@Param("limit") Integer limit);

    //查询权限集合
    @Select("select * from sys_menu")
    List<SysMenu> findMone();
    @Select("select * from sys_menu where parent_id = #{parentId}")
    List<SysMenu> findMtow(int parentId);

    //添加
    @Insert("insert into sys_role_menu VALUES(null,#{roleId},#{menuId})")
    void add(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);
    //删除
    @Delete("delete from sys_role_menu where role_id = #{id}")
    void delete(@Param("id") Integer id);

}
