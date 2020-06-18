package com.xr.base.mapper;

import com.xr.base.entity.SysDept;
import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysEmpMapper {
    /**
     * 登入
     */
    @Select("select * from sys_emp where username = #{username} and `password` = #{password}")
    SysEmp Login(SysEmp sysEmp);
    /**
     * 根据用户名查询用户信息
     * @return
     */
    @Select("select * from sys_emp where username = #{username}")
    public SysEmp findUserByUserName(String username);

    /**
     * 根据用户名获得角色的集合
     * @param username
     * @return
     */
    @Select("select r.name from sys_emp e,sys_role r ,sys_emp_role er where e.id = er.emp_id and r.id = er.role_id and e.username = #{username}")
    public List<String> findUserRoles(String username);

    /**
     * 查询所有员工
     */
    @Select({"<script>"
            ,"SELECT e.id,e.`name`,e.phone,e.username,e.state ,\n" +
            "(SELECT name from sys_role r where e.role_id = r.id)AS rname,\n" +
            "(SELECT dept_name from sys_dept d where e.dept_id = d.id) AS dname\n" +
            " FROM sys_emp e where 1=1" ,
            "<when test = 'name!=null'>" ,
                "and e.`name` like '%${name}%'",
            "</when>",
            " LIMIT #{page},#{count} ",
            "</script>"})
    @Results({
            @Result(column ="id",property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "username", property = "username"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "state", property = "state"),
            @Result(column = "dname", property = "Dname"),
            @Result(column = "rname", property = "Rname")
            //  one = @One(select = "com.xr.base.mapper.queryrole", fetchType = FetchType.DEFAULT)
    })
    public List<SysEmp> list(@Param("name")String name, @Param("page") Integer page,@Param("count") Integer count);
    @Select("select count(*) from sys_emp")
    public int empsize();
    /**
     * 增加
     */
    @Insert("INSERT INTO sys_emp VALUES(NULL,#{name},#{sex},#{age},#{education},#{politics},#{phone},#{dept_id},#{username},#{password},#{role_id},#{date},#{create_id},#{create_name},#{state})")
    void add(SysEmp sysEmp);
    /**
     * 删除
     */
    @Delete("delete from sys_emp where id = #{id}")
    void dele(int id);
    /**
     * 查询单个
     */
    @Select("select * from sys_emp where id = #{id}")
    SysEmp findOne(int id);
    /**
     * 修改
     */
    void update(SysEmp sysEmp);

    //查询用户所有角色
    @Select("SELECT * FROM sys_role r , sys_emp e , sys_emp_role er where e.id = er.emp_id and r.id = er.role_id and e.`name` = #{name}")
    List<SysRole> findRoles(String name);
    //查询用户所有权限
    @Select("SELECT * FROM sys_menu m , sys_role r, sys_role_menu rm ,sys_emp e ,sys_emp_role er where e.id = er.emp_id and r.id = er.role_id and r.id = rm.role_id and m.id = rm.menu_id and e.`name` = #{name}")
    List<SysMenu> findMenu(String name);

}
