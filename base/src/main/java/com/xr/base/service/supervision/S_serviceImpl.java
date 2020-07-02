package com.xr.base.service.supervision;

import com.xr.base.entity.Supervision;
import com.xr.base.mapper.S_mapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S_serviceImpl implements S_service {

    @Autowired
    private S_mapper s_mapper;
    @Override
    public int ins(Supervision s) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        s.getCreateTime(currentDate);
        //取得当前登录的用户名
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        s.setCreator(loginAccount);
        s.setId(null);
        return s_mapper.Ins(s);
    }

    @Override
    public List<Supervision> list(Integer page,Integer limit) {
        return s_mapper.list(page,limit);
    }

    @Override
    public int del(int id) {
        return s_mapper.Del(id);
    }

    @Override
    public int upd(Supervision s) {
        return s_mapper.update(s);
    }

    @Override
    public int updZt(Supervision s) {
        return s_mapper.updateZT(s);
    }

    @Override
    public int deleteZT(Supervision superviseReport) {
        return s_mapper.deleteZT(superviseReport);
    }

    @Override
    public List<Supervision> listUp(Integer page, Integer limit) {
        return s_mapper.listUp(page, limit);
    }
}
