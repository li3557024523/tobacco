package com.xr.base.service.impl;

import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import com.xr.base.mapper.SysEmpMapper;
import com.xr.base.service.SysEmpService;
import com.xr.base.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysEmpServiceImpl implements SysEmpService {

    @Autowired
    private SysEmpMapper sysEmpMapper;

    @Override
    public SysEmp Login(SysEmp sysEmp) {
        return sysEmpMapper.Login(sysEmp);
    }

    @Override
    public SysEmp findUserByUserName(String username) {
        return sysEmpMapper.findUserByUserName(username);
    }

    @Override
    public List<String> findUserRoles(String username) {
        return sysEmpMapper.findUserRoles(username);
    }

    @Override
    public List<SysRole> findUserRolesList() {
        return sysEmpMapper.findUserRolesList();
    }

    @Override
    public List<SysEmp> list(String name,Integer page,Integer count) {
        return sysEmpMapper.list(name,page,count);
    }

    @Override
    public int empsize() {
        return sysEmpMapper.empsize();
    }

    @Override
    public List<SysRole> findRoles(String name) {
        return sysEmpMapper.findRoles(name);
    }

    @Override
    public List<SysMenu> findMenu(String name) {
        return sysEmpMapper.findMenu(name);
    }

    @Override
    public void dele(int id) {
        sysEmpMapper.dele(id);
    }

    @Override
    public void add(SysEmp sysEmp) {
        sysEmpMapper.add(sysEmp);
    }

    @Override
    public void addrole(Integer empId, Integer roleId) {
        sysEmpMapper.addrole(empId,roleId);
    }

    @Override
    public void update(SysEmp sysEmp) {
        sysEmpMapper.update(sysEmp);
    }

    @Override
    public void updaterole(Integer empId, Integer roleId) {
        sysEmpMapper.updaterole(empId, roleId);
    }

    @Override
    public SysEmp findOne(int id) {
        return sysEmpMapper.findOne(id);
    }
}
