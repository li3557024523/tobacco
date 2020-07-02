package com.xr.base.mapper;

import com.xr.base.entity.Datum;
import com.xr.base.entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface D_mapper {
    @Select("select * from datum limit #{page},#{limit}")
    public List<Datum> D_list(@Param("page") Integer page, @Param("limit") Integer limit);
    @Insert("insert into datum(title,fileName,fileAddress,creatId,creatName,state,creatDate) values(#{d.title}," +
            "#{d.fileName},#{d.fileAddress},#{d.creatId},#{d.creatName},#{d.state},#{d.creatDate})")
    public int D_ins(@Param("d") Datum d);

    @Select("select * from datum where title like concat( '%',replace('${title}',' ',''), '%')  " +
            "  limit #{page},#{limit} ")
    public List<Datum> D_ListByType(@Param("page")Integer page, @Param("limit")Integer limit, @Param("title")String title);

    @Update("update  datum set  title=#{d.title},fileName=#{d.fileName},fileAddress=#{d.fileAddress},state=#{d.state} " +
            " where id=#{d.id} ")
    public int D_Upd(@Param("d")Datum d);
    @Delete("delete from datum where id=#{id}")
    public int D_del(Integer id);
}
