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
    public List<TRiskGuard> queryRisk(Integer pid,Integer did) {
        System.out.println(tRiskGuardMapper.RickAll(pid,did));
        return tRiskGuardMapper.RickAll(pid,did);
    }

    @Override
    public List<TRiskGuard> queryJobs() {
        return null;
    }

    @Override
    public List<TRiskGuard> listWhere(int pid) {
        return tRiskGuardMapper.listWhere(pid);
    }

    @Override
    public void add(TRiskGuard tRiskGuard) {
        System.out.println(tRiskGuard.getRiskL());
        System.out.println(tRiskGuard.getRiskC());

        tRiskGuard.setRiskD(tRiskGuard.getRiskL()*tRiskGuard.getRiskC());

        System.out.println(tRiskGuard.getRiskD());
        if (tRiskGuard.getRiskD() <= 90){
            tRiskGuard.setRiskGrade("一级风险");
        }else if(tRiskGuard.getRiskD() > 90 && tRiskGuard.getRiskD() <= 240){
            tRiskGuard.setRiskGrade("二级风险");
        }else {
            tRiskGuard.setRiskGrade("三级风险");
        }

        tRiskGuardMapper.add(tRiskGuard);
    }


}
