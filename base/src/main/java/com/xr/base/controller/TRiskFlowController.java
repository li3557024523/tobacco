package com.xr.base.controller;


import com.xr.base.entity.TRiskFlow;
import com.xr.base.service.TRiskFlowService;
import com.xr.base.util.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("flow")
public class TRiskFlowController {

    @Autowired
    private TRiskFlowService tRiskFlowService;

    @PostMapping("list")
    public ResponseResult list(@RequestParam(value = "qyear") String qyear,String pgname){

        System.out.println(pgname+"-------------------"+qyear);

        System.out.println(tRiskFlowService.list(pgname,qyear));

        List<TRiskFlow> list = tRiskFlowService.list(pgname,qyear);
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        return result;
    }


    @RequestMapping("delete")
    @RequiresPermissions("flow:delete")
    public ResponseResult delete(Integer id){
        tRiskFlowService.delete(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }
}
