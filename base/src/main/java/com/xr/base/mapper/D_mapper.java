package com.xr.base.mapper;

import com.xr.base.entity.Datum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface D_mapper {
    @Select("select * from datum limit #{page},#{limit}")
    public List<Datum> D_list(@Param("page") Integer page, @Param("limit") Integer limit);
    @Insert("insert into datum(title,fileName,fileAddress,creatId,creatName,state,creatDate) values(#{d.title}," +
            "#{d.fileName},#{d.fileAddress},#{d.creatId},#{d.creatName},#{d.state},#{d.creatDate})")
    public int D_ins(@Param("d") Datum d);
}
