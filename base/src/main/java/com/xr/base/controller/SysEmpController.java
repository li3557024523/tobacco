package com.xr.base.controller;

import com.xr.base.entity.SysEmp;
import com.xr.base.service.SysEmpService;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emp")
@CrossOrigin
public class SysEmpController {
    @Autowired
    private SysEmpService sysEmpService;

    @RequestMapping("login")
    public ResponseResult login(@RequestBody SysEmp sysEmp) {
        SysEmp loginUser = sysEmpService.Login(sysEmp);
        ResponseResult result = new ResponseResult();
        if (loginUser != null) {
            // 返回给前台的toke，唯一标识用户
            result.getData().put("token", loginUser.getUsername());
        } else {
            //登录失败
            result.getData().put("message", "登录失败，用户名或密码错误");
        }
        return result;
    }

    @RequestMapping("info")
    public ResponseResult info(String token){
        // 根据用户名查询用户信息
        SysEmp user = sysEmpService.findUserByUserName(token);
        // 根据用户名查询角色信息
        List<String> roles =  sysEmpService.findUserRoles(token);
        ResponseResult result = new ResponseResult();
        result.getData().put("roles",roles);
        result.getData().put("introduction","简介");
        result.getData().put("avatar","头像");
        result.getData().put("name",user.getUsername());
        return result;
    }

    @RequestMapping("list")
    public ResponseResult list(SysEmp sysUser,Integer page,Integer limit){
        List<SysEmp> list = sysEmpService.list();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",list.size());
        return result;
    }

}
