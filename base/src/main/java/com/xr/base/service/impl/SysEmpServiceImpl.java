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
    public List<SysEmp> list(SysEmp sysEmp,Integer page,Integer count) {
        return sysEmpMapper.list(sysEmp,page,count);
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
}
