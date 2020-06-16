package com.xr.base.service.impl;

import com.xr.base.entity.SysEmp;
import com.xr.base.mapper.SysEmpMapper;
import com.xr.base.service.SysEmpService;
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
    public List<SysEmp> list() {
        return sysEmpMapper.list();
    }
}
