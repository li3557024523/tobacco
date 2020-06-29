package com.xr.base.service;

import com.xr.base.entity.SysRole;
import com.xr.base.entity.TRiskGuard;

import java.util.List;

public interface TRiskGuardService {
    /**
     * 删除
     */
    void delete(int id);
    /**
     * 新增
     */
    void add(TRiskGuard tRiskGuard);

    /**
     * 查询所有风险
     * @return
     */
    List<TRiskGuard> queryRisk(Integer pid,Integer did);

    /**
     * 条件查询
     * @return
     */
    List<TRiskGuard> queryJobs();


    /**
     * 查询角色
     * @return
     */
    List<TRiskGuard> listWhere(int pid);


}
