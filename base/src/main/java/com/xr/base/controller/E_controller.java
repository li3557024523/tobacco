package com.xr.base.controller;

import com.xr.base.entity.Education;
import com.xr.base.entity.SysEmp;
import com.xr.base.service.education.E_service;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
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
    public ResponseResult listtype( Integer page,  Integer limit, Integer listType,  String title) {
        System.out.println(title);
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        System.out.println("limit:"+limit+"page:"+page);
        List<Education> list = e_service.E_ListByType(listType,(page-1)*limit,limit,title);
        System.out.println(list.size()+"list.size");
        ResponseResult result = new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }

    @RequestMapping("/upd")
    //@RequiresPermissions("user:list")
    public ResponseResult Eupd( @RequestBody Education temp ) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);

        int i=e_service.E_Upd(temp);

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
    public ResponseResult Sel( @RequestBody Education temp ) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);
        int i=0;
        if (temp.getInformationTypes()==null) {
            temp.setInformationTypes(1);
        }
        System.out.println(temp.getInformationTypes());
      if(temp!=null){
           i=e_service.E_Ins(temp);
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

    @RequestMapping("/del")
    public ResponseResult Del( Integer id){
        int i=e_service.E_Del(id);
        ResponseResult result = new ResponseResult();
        if(i>0){
            result.getData().put("message", "success");
            System.out.println("成功删除");
        }else{
            result.getData().put("message", "fail");
        }
        return result;
    }
}
