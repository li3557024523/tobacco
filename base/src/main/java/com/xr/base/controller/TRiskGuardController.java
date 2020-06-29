package com.xr.base.controller;


import com.xr.base.entity.SysEmp;
import com.xr.base.entity.TRiskGuard;
import com.xr.base.service.TRiskGuardService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

    @PostMapping("add")
    @RequiresPermissions("risk:add")
    public ResponseResult add(TRiskGuard tRiskGuard){

        //得到session
        Session session = SecurityUtils.getSubject().getSession();

        //把session里面的数据存入用户表里
        SysEmp sss = (SysEmp)session.getAttribute("USER_SESSION");

        //得到id  和name
        tRiskGuard.setCreateBy(sss.getId());
        tRiskGuard.setCreateName(sss.getName());

        System.out.println(tRiskGuard);

        tRiskGuardService.add(tRiskGuard);

        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }

    @PostMapping("list")
    public ResponseResult list(@RequestParam(value = "poid") Integer poid, Integer doid){

        System.out.println(poid+"----"+doid);

        List<TRiskGuard> list = tRiskGuardService.queryRisk(poid,doid);
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        return result;
    }



}
