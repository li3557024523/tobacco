package com.xr.base.mapper;

import com.xr.base.entity.SysDept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysDeptMapper {

    @Select("select * from sys_dept")
    List<SysDept> DeptList();

    @Select("SELECT leadership from sys_dept GROUP BY leadership")
        List<SysDept> leadership();

    @Select("select * from sys_dept where parent_id = #{parent_id}")
    List<SysDept> groupByFidList(int fid);

    @Select({"<script>"
            , "select parent_id,id,did,dept_name,principal_name,leadership,state" ,
            " FROM sys_dept where 1=1" ,
            "<when test = 'name!=null'>" ,
            "and dept_name like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{limit} ",
            "</script>"})
    List<SysDept> groupDept(@Param("name")String name, @Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select count(*) from sys_dept")
    int deptsize();

    @Insert("INSERT into sys_dept VALUES(NULL,#{e.deptName},NULL,#{e.parentId},#{e.principalName},NULL,#{e.leadership},NULL, NULL , NULL , #{e.state})")
    void add(@Param("e") SysDept sysDept);

    @Update("" +
            "UPDATE sys_dept SET " +
            "dept_name = #{e.deptName},principal_name = #{e.principalName},leadership = #{e.leadership},state = #{e.state},parent_id = #{e.parentId} " +
            "where id = #{e.id} ")
    void update(@Param("e") SysDept sysDept);

    @Select("SELECT * from sys_dept where id = #{id}")
    SysDept findOne(Integer id);

    @Delete("delete from sys_dept where id = #{id}")
    void dele(int id);
}
