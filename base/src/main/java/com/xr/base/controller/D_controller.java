package com.xr.base.controller;

import com.xr.base.entity.Datum;
import com.xr.base.service.datum.D_service;
import com.xr.base.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

    @PostMapping("/uploadCategory")
    @ResponseBody
    public void uploadImg(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request, HttpServletResponse resp)throws Exception {
        if(request.getCharacterEncoding()==null) {
            request.setCharacterEncoding("UTF-8");
        }
        String fileName=file.getOriginalFilename();
        //String suffxName=fileName.substring(fileName.lastIndexOf("."));
        String filePath="C:\\Users\\Administrator\\IdeaProjects\\tobacco\\base\\src\\main\\resources\\file";
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            ServletOutputStream out=resp.getOutputStream();
            OutputStreamWriter ow=new OutputStreamWriter(out,"UTF-8");
            ow.write(filePath+fileName);
            ow.flush();
            ow.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
