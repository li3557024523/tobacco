package com.xr.base.service;


import com.xr.base.entity.TRiskFlow;

import java.util.List;



public interface TRiskFlowService {
    /**
     * 查询所以
     */
    List<TRiskFlow> list(String pgname,String qyear);

    /**
     * 删除
     */
    void delete(int id);


}
