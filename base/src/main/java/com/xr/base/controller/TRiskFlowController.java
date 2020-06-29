package com.xr.base.controller;


import com.xr.base.entity.TRiskFlow;
import com.xr.base.service.TRiskFlowService;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Flow")
public class TRiskFlowController {

    @Autowired
    private TRiskFlowService tRiskFlowService;

    @PostMapping("list")
    public ResponseResult list(){
        List<TRiskFlow> list = tRiskFlowService.list();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        return result;
    }
}