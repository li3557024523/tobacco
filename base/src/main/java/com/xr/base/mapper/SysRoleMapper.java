package com.xr.base.mapper;

import com.xr.base.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    /**
     * 查询角色
     */
    @Select({"<script>"
            , "SELECT d.id as did,r.id,r.`name`,r.create_date,r.state,r.remark,d.dept_name as deptName from sys_role r , sys_dept d , sys_role_dept rd where r.id = rd.role_id and d.id = rd.dept_id" ,
            " and 1=1" ,
            "<when test = 'name!=null'>" ,
            "and `name` like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{limit} ",
            "</script>"})
    @Results({
            @Result(column = "did", property = "did"),
            @Result(column = "deptName", property = "deptName")
    })
    List<SysRole> findrole(@Param("name") String name , @Param("page") Integer page , @Param("limit") Integer limit);
    @Select("select count(id) from sys_role")
    int rolesize();

    /**
     * 添加角色，角色部门
     */
    @Insert("insert into sys_role (id, name, remark, create_date, create_id, create_name, state)\n" +
            " values\n" +
            "        (null, #{r.name}, #{r.remark}, NOW(), null,null, #{r.state})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    void add(@Param("r") SysRole sysRole);
    @Insert("insert into sys_role_dept values (null,#{roleId} , #{deptId})")
    void adddept(@Param("roleId")int roleId ,@Param("deptId") int deptId);
    /**
     * 删除角色
     */
    @Delete("delete from sys_role where id = #{id}")
    void delete(int id);
    /**
     * 修改角色
     */
    @Update("update sys_role set name=#{r.name}, remark = #{r.remark} ,state = #{r.state}  where id = #{r.id}")
    void update(@Param("r") SysRole sysRole);
    //修改部门
    @Update("update sys_role_dept set dept_id = #{deptId} where role_id = #{roleId}")
    void updeptname(@Param("roleId")Integer roleId ,@Param("deptId") Integer deptId);
}
