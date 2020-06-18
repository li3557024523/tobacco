package com.xr.base.service.education;

import com.xr.base.entity.Education;
import com.xr.base.mapper.E_mapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.sql.Date;
import java.util.List;

@Service
public class E_serviceImpl implements E_service {
    @Autowired
    private E_mapper e_mapper;
    @Override
    public List<Education> E_List() {
        return e_mapper.E_List();
    }

    @Override
    public Education E_SelByTitle(String title) {
        return e_mapper.E_SelByTitle(title);
    }

    @Override
    public int E_Ins(Education e) {
        return e_mapper.E_Ins(e);
    }

    @Override
    public int E_Upd(Education e) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        e.setPubdate(currentDate);
        //取得当前登录的用户名
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        e.setCreator(loginAccount);
        return e_mapper.E_Upd(e);
    }

    @Override
    public int E_Del(int id) {
        return e_mapper.E_Del(id);
    }

    @Override
    public List<Education> E_ListByType(Integer type,Integer page,Integer limit,String title) {
        if(title==null){
            title="";
        }
       System.out.println("title:"+title);
        System.out.println("page:"+page+"type:"+type);

        return e_mapper.E_ListByType(type,page,limit,title);
    }
}
