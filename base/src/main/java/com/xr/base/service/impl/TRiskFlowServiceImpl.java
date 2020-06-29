package com.xr.base.service.impl;

import com.xr.base.entity.TRiskFlow;
import com.xr.base.mapper.TRiskFlowMapper;
import com.xr.base.service.TRiskFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRiskFlowServiceImpl implements TRiskFlowService {

    @Autowired
    TRiskFlowMapper tRiskFlowMapper;

    @Override
    public List<TRiskFlow> list(String pgname,String qyear) {

        System.out.println("ServiveImpl的实现123123---"+tRiskFlowMapper.list(pgname,qyear));

        return tRiskFlowMapper.list(pgname,qyear);
    }

    @Override
    public void delete(int id) {
        tRiskFlowMapper.delete(id);
    }
}
