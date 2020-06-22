package com.xr.base.mapper;

import com.xr.base.entity.Datum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface D_mapper {
    @Select("select * from datum limit #{page},#{limit}")
    public List<Datum> D_list(@Param("page") Integer page, @Param("limit") Integer limit);
}
