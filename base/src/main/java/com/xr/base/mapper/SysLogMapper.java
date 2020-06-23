package com.xr.base.mapper;

import com.xr.base.entity.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper {
    @Insert("insert into sys_log values (null,#{name},#{username},#{url},#{operation},#{ip},#{serverip},#{operationData},#{client},#{browser},NOW())")
    void add(SysLog sysLog);

    @Select({"<script>"
            , "SELECT * from sys_log" ,
            " where 1=1" ,
            "<when test = 'name!=null'>" ,
            "and operation like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{limit} ",
            "</script>"})
    List<SysLog> list(@Param("name") String name , @Param("page") Integer page , @Param("limit") Integer limit);
    @Select("select count(*) from sys_log")
    int logsize();

    @Delete("delete from sys_log where id = #{id}")
    void delete(@Param("id") int id);
}
