package com.xr.base.controller;

import com.xr.base.entity.SysLog;
import com.xr.base.service.SysLogService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("list")
    @RequiresPermissions("log:list")
    public ResponseResult list(String name, int page , int limit){
        List<SysLog> list = sysLogService.list(name, page, limit);
        int logsize = sysLogService.logsize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",logsize);

        return  result;
    }

    @RequestMapping("delete")
    @RequiresPermissions("log:delete")
    public ResponseResult delete(int id){
        sysLogService.delete(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return  result;
    }

}
