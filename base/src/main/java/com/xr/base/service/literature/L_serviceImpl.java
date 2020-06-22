package com.xr.base.service.literature;

import com.xr.base.entity.Literature;
import com.xr.base.mapper.L_mapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class L_serviceImpl implements L_service {
    @Autowired
    private L_mapper l_mapper;
    @Override
    public List<Literature> list(Integer page,Integer limit) {
        return l_mapper.list(page,limit);
    }

    @Override
    public int upd(Literature l) {
        return l_mapper.upd(l);
    }

    @Override
    public int ins(Literature l) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        l.setCreateDate(currentDate);
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        l.setCreator(loginAccount);
        return l_mapper.ins(l);
    }
}
