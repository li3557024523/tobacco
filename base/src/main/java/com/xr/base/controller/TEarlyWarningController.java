package com.xr.base.controller;

import com.xr.base.entity.TEarlyWarning;
import com.xr.base.service.TEarlyWarningService;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Point")
public class TEarlyWarningController {
    @Autowired
    private TEarlyWarningService tRiskFlowService;

    @PostMapping("list")
    public ResponseResult list(){
        List<TEarlyWarning> list = tRiskFlowService.list();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        return result;
    }




}
