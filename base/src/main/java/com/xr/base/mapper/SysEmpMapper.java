package com.xr.base.mapper;

import com.xr.base.entity.SysEmp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * from sys_emp")
    public List<SysEmp> list();
}
