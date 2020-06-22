package com.xr.base.controller;

import com.xr.base.entity.SysMenu;
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
        System.out.println("菜单之"+name+""+page+""+limit);
        List<SysRole> list = sysMenuService.roleList(name,(page-1)*limit, limit);
        int rolesize = sysRoleService.rolesize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",rolesize);
        return result;
    }
    @RequestMapping("mlist")
    public ResponseResult Mlist(){
        List<SysMenu> mone = sysMenuService.findMone();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",mone);
        return result;
    }

    @RequestMapping("add")
    public ResponseResult add(Integer roleId,String menuId){
        String[] arr = menuId.split(",");
        for (int i = 0 ; i <= arr.length-1 ; i++){
            sysMenuService.add(roleId, Integer.parseInt(arr[i]));
        }
        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }

    @RequestMapping("delete")
    public ResponseResult delete(Integer id){
        sysMenuService.delete(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("update")
    public ResponseResult update(Integer roleId,String menuId,Integer id){
        sysMenuService.delete(roleId);
        String[] arr = menuId.split(",");
        for (int i = 0 ; i <= arr.length-1 ; i++){
            sysMenuService.add(roleId, Integer.parseInt(arr[i]));
        }
        ResponseResult result = new ResponseResult();
        result.getData().put("message","修改成功");
        return result;
    }
}
