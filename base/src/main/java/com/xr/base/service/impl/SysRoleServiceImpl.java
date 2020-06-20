package com.xr.base.service.impl;

import com.xr.base.entity.SysRole;
import com.xr.base.mapper.SysRoleMapper;
import com.xr.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findrole(String name , Integer page , Integer limit) {
        return sysRoleMapper.findrole(name, page, limit);
    }

    @Override
    public int rolesize() {
        return sysRoleMapper.rolesize();
    }

    @Override
    public void add(SysRole sysRole) {
        sysRoleMapper.add(sysRole);
    }

    @Override
    public void adddept(int roleId, int deptId) {
        sysRoleMapper.adddept(roleId, deptId);
    }

    @Override
    public void delete(int id) {
        sysRoleMapper.delete(id);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void updeptname(Integer roleId, Integer deptId) {
        sysRoleMapper.updeptname(roleId,deptId);
    }
}
