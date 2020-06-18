package com.xr.base.service.education;

import com.xr.base.entity.Education;

import java.util.List;

public interface E_service {
    public List<Education> E_List();
    public Education E_SelByTitle(String title);
    public int E_Ins(Education e);
    public int E_Upd(Education e);
    public int E_Del(int id);
    public List<Education> E_ListByType(Integer type, Integer page, Integer limit,String title);
}
