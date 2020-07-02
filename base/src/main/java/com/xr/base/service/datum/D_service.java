package com.xr.base.service.datum;

import com.xr.base.entity.Datum;

import java.util.List;

public interface D_service {
    public List<Datum> D_list(Integer page,Integer limit);
    public int D_Ins(Datum d);
    public List<Datum> D_ListByType(Integer page,Integer limit,String title);
    public int D_upd(Datum d);
    public int D_del(Integer id);
}
