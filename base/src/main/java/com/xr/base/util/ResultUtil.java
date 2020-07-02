package com.xr.base.util;

import com.xr.base.util.ResultEnum;

import java.util.Map;

public class ResultUtil {

    /**成功且带数据**/
    public static ResponseResult success(Object object){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMsg());
        result.setData((Map<String, Object>) object);
        return result;
    }
    /**成功但不带数据**/
    public static ResponseResult success(){

        return success(null);
    }
    /**失败**/
    public static ResponseResult error(Integer code,String msg){
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}