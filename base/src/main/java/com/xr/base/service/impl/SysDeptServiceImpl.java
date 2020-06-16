package com.xr.base.service.impl;

import com.xr.base.entity.SysDept;
import com.xr.base.mapper.SysDeptMapper;
import com.xr.base.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> groupDept() {
        return sysDeptMapper.groupDept();
    }
}
