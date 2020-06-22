package com.xr.base.controller;


import com.xr.base.entity.SysRole;
import com.xr.base.entity.TRiskGuard;
import com.xr.base.service.TRiskGuardService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("risk")
public class TRiskGuardController {
    @Autowired
    private TRiskGuardService tRiskGuardService;

    @RequestMapping("delete")
    @RequiresPermissions("risk:delete")
    public ResponseResult delete(Integer id){
        tRiskGuardService.delete(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("add")
    @RequiresPermissions("risk:add")
    public ResponseResult add(TRiskGuard tRiskGuard){
        //获取系统当前时间
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=null;
        try {
            time=sdf.parse(sdf.format(new Date()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        tRiskGuard.setCreateTime((Time) time);
        tRiskGuardService.add(tRiskGuard);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }

    @PostMapping("list")
    public ResponseResult list(){
        List<TRiskGuard> list = tRiskGuardService.queryRisk();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        return result;
    }

    @PostMapping("queryRole")
    public ResponseResult queryRole(){
        List<SysRole> list = tRiskGuardService.queryRole();
        ResponseResult result = new ResponseResult();
        result.getData().put("queryRole",list);
        return result;
    }




}
