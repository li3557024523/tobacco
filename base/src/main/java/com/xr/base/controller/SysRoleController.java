package com.xr.base.controller;

import com.xr.base.entity.SysDept;
import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysRole;
import com.xr.base.service.SysRoleService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("list")
    @RequiresPermissions("role:list")
    public ResponseResult findRole(String name , Integer page , Integer limit){
        System.out.println(name+""+page+""+limit);
        List<SysRole> list = sysRoleService.findrole(name,(page-1)*limit, limit);
        int rolesize = sysRoleService.rolesize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",rolesize);
        return result;
    }

    @RequestMapping("delete")
    @RequiresPermissions("role:delete")
    public ResponseResult delete(int id){
        sysRoleService.delete(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("add")
    @RequiresPermissions("role:add")
    public ResponseResult add( SysRole sysRole ,int did){
//        //获取系统当前时间
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date time=null;
//        try {
//            time=sdf.parse(sdf.format(new Date()));
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        sysRole.setCreateDate((Time) time);
        Session session = SecurityUtils.getSubject().getSession();
        SysEmp sss = (SysEmp)session.getAttribute("USER_SESSION");
        sysRole.setCreateId(sss.getCreateId());
        sysRole.setCreateName(sss.getCreateName());
        sysRoleService.add(sysRole);
        sysRoleService.adddept(sysRole.getId() , did);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }

    @RequestMapping("update")
    @RequiresPermissions("role:update")
    public ResponseResult update(SysRole sysRole , Integer did){
        sysRoleService.update(sysRole);
        System.out.println(sysRole.getId()+"aa"+did);
        sysRoleService.updeptname(sysRole.getId(),did);
        ResponseResult result =new ResponseResult();
        result.getData().put("message","修改成功");
        return result;
    }

}
