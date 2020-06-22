package com.xr.base.service.impl;

import com.xr.base.entity.SysRole;
import com.xr.base.entity.TRiskGuard;
import com.xr.base.mapper.TRiskGuardMapper;
import com.xr.base.service.TRiskGuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRiskGuardServiceImpl implements TRiskGuardService {

    @Autowired
    TRiskGuardMapper tRiskGuardMapper;


    @Override
    public void delete(int id) {
        tRiskGuardMapper.delete(id);
    }

    @Override
    public void add(TRiskGuard tRickGuard) {
        tRiskGuardMapper.add(tRickGuard);
    }

    @Override
    public List<TRiskGuard> queryRisk() {
        System.out.println(tRiskGuardMapper.RickAll());
        return tRiskGuardMapper.RickAll();
    }

    @Override
    public List<TRiskGuard> queryJobs() {
        return null;
    }

    @Override
    public List<SysRole> queryRole() {
        return tRiskGuardMapper.queryRole();
    }


}
