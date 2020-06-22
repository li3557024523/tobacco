package com.xr.base.controller;

import com.xr.base.entity.Education;
import com.xr.base.entity.Literature;
import com.xr.base.service.literature.L_service;
import com.xr.base.util.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/literature")
public class L_controller {
    @Autowired
    private L_service l_service;
    @RequestMapping(value = "/listType")
    public ResponseResult list( Integer page, Integer limit){
        List<Literature> list=l_service.list((page-1)*limit,limit);
        ResponseResult result=new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }
    @RequestMapping(value = "/upd")
    public ResponseResult upd(@RequestBody Literature temp){
        int i=l_service.upd(temp);

        ResponseResult result = new ResponseResult();
        if(i>0){
            result.getData().put("message", "success");
            System.out.println("成功更新");
        }else{
            result.getData().put("message", "fail");
        }

        return result;
    }
    @RequestMapping("/ins")
    //@RequiresPermissions("user:list")
    public ResponseResult Sel( @RequestBody Literature temp ) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);
        int i=0;
        temp.setId(null);
        if(temp!=null){
            i=l_service.ins(temp);
        }

        ResponseResult result = new ResponseResult();
        if(i>0){
            result.getData().put("message", "success");
            System.out.println("成功添加");
        }else{
            result.getData().put("message", "fail");
        }

        return result;
    }
}
