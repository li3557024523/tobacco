package com.xr.base.controller;

import com.xr.base.entity.SysDept;
import com.xr.base.entity.SysEmp;
import com.xr.base.service.SysDeptService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

//    @RequestMapping("groupDept")
//    public ResponseResult groupDept(int id){
//        ResponseResult result = new ResponseResult();
//        result.getData().put("deptList",sysDeptService.groupByFidList(id));
//        return result;
//    }

    @RequestMapping("deptlist")
    public ResponseResult deptList(){
        ResponseResult result = new ResponseResult();
        result.getData().put("dept",sysDeptService.DeptList());
        return result;
    }

    @RequestMapping("list")
    public ResponseResult list(String name, Integer page, Integer limit){
        List<SysDept> list = sysDeptService.groupDept(name,(page-1)*limit, limit);
        int empsize = sysDeptService.deptsize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",empsize);
        return result;
    }

    @RequestMapping("delete")
    @RequiresPermissions("dept:delete")
    public ResponseResult delete(int id){
        sysDeptService.dele(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("add")
    @RequiresPermissions("dept:add")
    public ResponseResult add(SysDept sysDept){
        //获取系统当前时间
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=null;
        try {
            time=sdf.parse(sdf.format(new Date()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        sysDept.setDate((Time) time);
        sysDeptService.add(sysDept);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }


}
