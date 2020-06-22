package com.xr.base.controller;

import com.xr.base.entity.Datum;
import com.xr.base.service.datum.D_service;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/datum")
public class D_controller {
    @Autowired
    private D_service d_service;
    public ResponseResult list(Integer page,Integer limit){
        List<Datum> list= d_service.D_list(page,limit);
        ResponseResult result=new ResponseResult();
        result.getData().put("item", list);
        result.getData().put("total", list.size());
        return result;
    }
}
