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


    @Select("select * from sys_dept where parent_id = #{parent_id}")
    List<SysDept> groupByFidList(int fid);

    @Select({"<script>"
            , "select id,did,dept_name,principal_name,leadership,state" ,
            " FROM sys_dept where 1=1" ,
            "<when test = 'name!=null'>" ,
            "and dept_name like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{limit} ",
            "</script>"})
    List<SysDept> groupDept(@Param("name")String name, @Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select count(*) from sys_dept")
    int deptsize();

    @Insert("INSERT into sys_dept VALUES(NULL,#{dept_name},NULL,NULL,#{principal_name},NULL,#{leadership},#{date}, #{create_id} , #{create_name} , #{state})")
    void add(SysDept sysDept);

    @Update("" +
            "UPDATE sys_dept SET " +
            "dept_name = #{dept_name},principal_name = #{principal_name},info = #{info},leadership = #{leadership},state = #{state} " +
            "where id = #{id} ")
    void update(SysDept sysDept);

    @Select("SELECT * from sys_dept where id = #{id}")
    SysDept findOne(Integer id);

    @Delete("delete from sys_dept where id = #{id}")
    void dele(int id);
}
