package com.xr.base.mapper;

import com.xr.base.entity.Literature;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface L_mapper {
    @Select("select * from literature where title like concat( '%',replace('${title}',' ',''), '%')  " +
            "  limit #{page},#{limit} ")
    public List<Literature> list(@Param("page")Integer page, @Param("limit")Integer limit, @Param("title")String title);
    @Update("update  literature set  title=#{l.title},contributor=#{l.contributor},state=#{l.state},context=#{l.context} where id=#{l.id} ")
    public int upd(@Param("l") Literature l);
    @Insert("insert into literature(title,contributor,context,createDate,createId,creator,state) values(#{l.title}," +
            "#{l.contributor},#{l.context},#{l.createDate},#{l.createId},#{l.creator},#{l.state}) ")
    public int ins(@Param("l") Literature l);
    @Delete("delete from literature where id=#{id}")
    public int D_del(Integer id);
}
