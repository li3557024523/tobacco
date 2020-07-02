package com.xr.base.service.datum;

import com.xr.base.entity.Datum;
import com.xr.base.mapper.D_mapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class D_serviceImpl implements D_service {
    @Autowired
    D_mapper d_mapper;

    @Override
    public List<Datum> D_list(Integer page,Integer limit) {
        return d_mapper.D_list(page,limit);
    }

    @Override
    public int D_Ins(Datum d) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        d.setCreatDate(currentDate);
        //取得当前登录的用户名
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        d.setCreatName(loginAccount);
        d.setId(null);
        return d_mapper.D_ins(d);
    }
}
