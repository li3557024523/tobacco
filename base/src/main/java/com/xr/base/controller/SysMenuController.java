package com.xr.base.controller;

import com.xr.base.entity.SysRole;
import com.xr.base.service.SysMenuService;
import com.xr.base.service.SysRoleService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("list")
    @RequiresPermissions("menu:list")
    public ResponseResult findRole(String name , Integer page , Integer limit){
        System.out.println(name+""+page+""+limit);
        List<SysRole> list = sysMenuService.roleList(name,(page-1)*limit, limit);
        int rolesize = sysRoleService.rolesize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",rolesize);
        return result;
    }
}
