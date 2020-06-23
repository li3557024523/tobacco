package com.xr.base.service.impl;

import com.xr.base.entity.SysLog;
import com.xr.base.mapper.SysLogMapper;
import com.xr.base.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void add(SysLog sysLog) {
        sysLogMapper.add(sysLog);
    }

    @Override
    public List<SysLog> list(String name, Integer page, Integer limit) {
        return sysLogMapper.list(name, page, limit);
    }

    @Override
    public int logsize() {
        return sysLogMapper.logsize();
    }

    @Override
    public void delete(int id) {
        sysLogMapper.delete(id);
    }
}
