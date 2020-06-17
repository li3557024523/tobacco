package com.xr.base.controller;

import com.xr.base.entity.SysEmp;
import com.xr.base.service.SysEmpService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
@CrossOrigin
public class SysEmpController {
    @Autowired
    private SysEmpService sysEmpService;

    @RequestMapping("login")
    public ResponseResult login(@RequestBody SysEmp sysEmp) {
//        SysEmp loginUser = sysEmpService.Login(sysEmp);
//        ResponseResult result = new ResponseResult();
//        if (loginUser != null) {
//            // 返回给前台的toke，唯一标识用户
//            result.getData().put("token", loginUser.getUsername());
//        } else {
//            //登录失败
//            result.getData().put("message", "登录失败，用户名或密码错误");
//        }
//        return result;
        ResponseResult result = new ResponseResult();
        UsernamePasswordToken token = new UsernamePasswordToken(sysEmp.getUsername(), sysEmp.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        result.getData().put("token",subject.getSession().getId());
        return result;
    }

    @RequestMapping("info")
    public ResponseResult info(String token){
        // 根据用户名查询用户信息
//        SysEmp user = sysEmpService.findUserByUserName(token);
//        // 根据用户名查询角色信息
//        List<String> roles =  sysEmpService.findUserRoles(token);
//        ResponseResult result = new ResponseResult();
//        result.getData().put("roles",roles);
//        result.getData().put("introduction","简介");
//        result.getData().put("avatar","头像");
//        result.getData().put("name",user.getUsername());
//        return result;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ResponseResult result = new ResponseResult();
        if(token.equals(subject.getSession().getId().toString())){
            // 从shiro的session里获得保存的用户信息
            SysEmp loginUser = (SysEmp) session.getAttribute("USER_SESSION");
            // 获得角色字符串集合
            List<String> roles = (List<String>) session.getAttribute("roles");
            if(loginUser!=null){
                // 根据用户获得角色字符串数组
                roles = sysEmpService.findUserRoles(loginUser.getUsername());
                result.getData().put("roles",roles);
                result.getData().put("introduction","介绍");
                result.getData().put("avatar","头像");
                result.getData().put("name",loginUser.getUsername());
                return result;
            }
        }
        return null;
    }

    @RequestMapping("list")
    public ResponseResult list(String name, Integer page, Integer limit){
        System.out.println(name);
        List<SysEmp> list = sysEmpService.list(name,(page-1)*limit, limit);
        int empsize = sysEmpService.empsize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",empsize);
        return result;
    }

    @RequestMapping("delete")
    @RequiresPermissions("emp:delete")
    public ResponseResult add(Integer id){
        sysEmpService.dele(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }
}
