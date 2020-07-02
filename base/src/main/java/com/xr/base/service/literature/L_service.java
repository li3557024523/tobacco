package com.xr.base.service.literature;

import com.xr.base.entity.Literature;

import java.util.List;

public interface L_service {
    public List<Literature> list(Integer page,Integer limit,String title);
    public int upd(Literature l);
    public int ins(Literature l);
    public int Del(Integer id);
}
