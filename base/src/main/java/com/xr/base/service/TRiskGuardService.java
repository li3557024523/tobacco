package com.xr.base.service;

import com.xr.base.entity.TRiskGuard;

import java.util.List;

public interface TRiskGuardService {
    /**
     * 删除
     */
    void dele(int id);
    /**
     * 新增
     */
    void add(TRiskGuard tRiskGuard);

    /**
     * 查询所有风险
     * @return
     */
    List<TRiskGuard> queryRisk();
}
