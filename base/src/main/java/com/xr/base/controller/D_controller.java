package com.xr.base.controller;

import com.xr.base.entity.Datum;
import com.xr.base.entity.Education;
import com.xr.base.service.datum.D_service;
import com.xr.base.util.ResponseResult;
import com.xr.base.util.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/datum")
public class D_controller {
    @Autowired
    private D_service d_service;
    @RequestMapping(value = "/listType")
    public ResponseResult list(Integer page,Integer limit){
        List<Datum> list= d_service.D_list((page-1)*limit,limit);
        ResponseResult result=new ResponseResult();
        result.getData().put("items", list);
        result.getData().put("total", list.size());
        return result;
    }
    @RequestMapping("/ins")
    //@RequiresPermissions("user:list")
    public ResponseResult Sel( @RequestBody Datum temp ) {
        //System.out.println(listType);
        //System.out.println("limit:"+limit+"page:"+page);
        int i=0;
        System.out.println(temp.getTitle());

        if(temp!=null){
            i=d_service.D_Ins(temp);
        }

        ResponseResult result = new ResponseResult();
        if(i>0){
            result.getData().put("message", "success");
            System.out.println("成功添加");
        }else{
            result.getData().put("message", "fail");
        }

        return result;
    }

    @RequestMapping(value = "/upload",method =RequestMethod.POST)
    public String upload( MultipartFile file) throws ParseException {
        //System.out.println("文件上传"+temp.getFileAddress()+"L"+temp.getCreatName());
        String filePath =  ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(filePath);
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀名
        //String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 解决中文问题,liunx 下中文路径,图片显示问题
        //fileName = UUID.randomUUID() + suffixName;
        File file1 = new File(filePath+"/statement/"+ originalFilename);
        System.out.println("path:"+filePath+"/statement/"+ originalFilename);
        String path="";
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);


            //d_service.insert(Integer.parseInt(rid),originalFilename,date,Integer.parseInt(creator),cname);
             path=filePath+"/statement/"+ originalFilename;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


}
