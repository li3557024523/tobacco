package com.xr.base.service.supervision;

import com.xr.base.entity.Supervision;

import java.util.List;

public interface S_service {

    public int ins(Supervision s);
    public List<Supervision> list(Integer page,Integer limit);
    public int  del(int id);
    public int  upd(Supervision s);
    public int updZt(Supervision s);
    int deleteZT(Supervision superviseReport);
}
