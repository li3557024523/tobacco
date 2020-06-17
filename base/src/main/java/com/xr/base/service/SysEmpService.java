package com.xr.base.service;

import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import com.xr.base.mapper.SysEmpMapper;

import java.util.List;

public interface SysEmpService {
    /**
     * 登入
     */
    SysEmp Login(SysEmp sysEmp);
    /**
     * 根据用户名查询用户信息
     * @return
     */
    SysEmp findUserByUserName(String username);
    /**
     * 根据用户名获得角色的集合
     * @param username
     * @return
     */
    List<String> findUserRoles(String username);
    /**
     * 重新所有员工
     */
    List<SysEmp> list(Integer page,Integer count);
    int empsize();

    /**
     * 角色集合 ， 权限集合
     */
    List<SysRole> findRoles(String name);
    List<SysMenu> findMenu(String name);
}
