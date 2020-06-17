package com.xr.base.controller;

import com.xr.base.entity.Education;
import com.xr.base.service.education.E_service;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/education")
public class E_controller {
    @Autowired
    private E_service e_service;
    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    public ResponseResult list(Integer page, Integer limit) {
        List<Education> list = e_service.E_List();
        ResponseResult result = new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }

    @RequestMapping("/listType")
    //@RequiresPermissions("user:list")
    public ResponseResult listtype(Integer page, Integer limit,Integer listType) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);
        List<Education> list = e_service.E_ListByType(listType,(page-1)*limit,limit);
        ResponseResult result = new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }

    @RequestMapping("/eupd")
    //@RequiresPermissions("user:list")
    public ResponseResult Eupd(Integer page, Integer limit,Integer listType) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);
        List<Education> list = e_service.E_ListByType(listType,(page-1)*limit,limit);
        ResponseResult result = new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }

    @GetMapping("e_del")
    public ModelAndView E_del(@RequestParam int id){
        ModelAndView mv=new ModelAndView();
        int i =e_service.E_Del(id);
        return mv;
    }
}
