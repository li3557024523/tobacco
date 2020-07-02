package com.xr.base.controller;

import com.xr.base.entity.Datum;
import com.xr.base.entity.Education;
import com.xr.base.entity.Supervision;
import com.xr.base.service.supervision.S_service;
import com.xr.base.util.ResponseResult;
import com.xr.base.util.UserUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Supervision")
public class S_controller {
    @Autowired
    public S_service s_service;

    @RequestMapping("/add")
    public ResponseResult Sel(@RequestBody Supervision temp){
        ResponseResult result=new ResponseResult();
        if(temp.getId()!=null){
            temp.setState(temp.getState()+1);
            s_service.upd(temp);
            result.getData().put("message", "修改成功");
        }else{
            if (temp.getState()==null){
        temp.setState(1);
            }else {
                temp.setState(temp.getState()+1);
            }
        temp.setCreator(UserUtil.getUserName());//登录用户
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();

        temp.setCreateId(1);  //登录人姓名
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        temp.setCreateTime(currentDate);
        s_service.ins(temp);
        result.getData().put("message","增加成功");
        }
        return result;
    }
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestParam("page") Integer page,@RequestParam("limit")Integer limit){
        List<Supervision> list= s_service.list(page,limit);
        ResponseResult result=new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }
    @RequestMapping(value = "/del")
    public ResponseResult del(@Param("id")Integer id){
        int i=s_service.del(id);
        ResponseResult result = new ResponseResult();
        if(i>0){
            result.getData().put("message", "success");
            System.out.println("成功删除");
        }else{
            result.getData().put("message", "fail");
        }
        return result;
    }

    @RequestMapping("/updateZT")
    public ResponseResult updateZT(Supervision superviseReport,Integer state){
        System.out.println(state);
        if(state==2){
            superviseReport.setState(2);
        }else if(state==3){
            superviseReport.setState(3);
        }else if(state==4){
            superviseReport.setState(4);
        }
        else if(state==0){
            superviseReport.setState(0);
        }
        s_service.updZt(superviseReport);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("/size")
    public ResponseResult listUp(){
            List<Supervision> list=s_service.listUp(0,10);
            int i=list.size();

            ResponseResult result=new ResponseResult();
            result.getData().put("size",i);
            return  result;

    }

    @RequestMapping("/update")
    public ResponseResult update(Supervision superviseReport){
        s_service.upd(superviseReport);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","修改成功");
        return result;
    }

    @RequestMapping("/deleteZT")
    public ResponseResult deleteZT(Supervision superviseReport,Integer state){
        System.out.println(state);
        if(state==2){
            superviseReport.setState(2);
        }else if(state==3){
            superviseReport.setState(3);
        }else if(state==4){
            superviseReport.setState(4);
        }
        else if(state==0){
            superviseReport.setState(0);
        }
        s_service.deleteZT(superviseReport);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }


}
