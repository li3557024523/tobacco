package com.xr.base.service.impl;

import com.xr.base.entity.SysRole;
import com.xr.base.mapper.SysMenuMapper;
import com.xr.base.service.SysMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> roleList( String name,  Integer page,Integer limit) {
        return sysMenuMapper.roleList(name, page, limit);
    }
}
