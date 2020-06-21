package com.xr.base.mapper;

import com.xr.base.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
