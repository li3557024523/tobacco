package com.xr.base.controller;

import com.xr.base.entity.SysDept;
import com.xr.base.service.SysDeptService;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("groupDept")
    public ResponseResult groupDept(){
        ResponseResult result = new ResponseResult();
        result.getData().put("deptList",sysDeptService.groupDept());
        return result;
    }
}
