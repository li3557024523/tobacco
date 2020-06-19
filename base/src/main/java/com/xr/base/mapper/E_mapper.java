package com.xr.base.mapper;

import com.xr.base.entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface E_mapper {
    @Select("select * from education ")
    public List<Education> E_List();
    @Select("select * from education where title like '%#{title}%'  ")
    public Education E_SelByTitle(@Param("title") String title);
    @Insert("insert into education(title,origin,informationTypes,context,pubdate,addTime,createId,creator,state) values(#{e.title" +
            "},#{e.origin},#{e.informationTypes},#{e.context},#{e.pubdate},#{e.addTime},#{e.createId},#{e.creator},#{e.state}) ")
    public int E_Ins(@Param("e") Education e);

    @Update("update  education set  title=#{e.title},origin=#{e.origin},informationTypes=#{e.informationTypes},context=#{e.context},pubdate=#{e.pubdate},addTime=#{e.addTime},createId=#{e.createId},creator=#{e.creator},state=#{e.state} " +
            " where id=#{e.id} ")
    public int E_Upd(@Param("e")Education e);
    @Select("delete from education where id=#{id}")
    public int E_Del(@Param("id") Integer id);
    @Select("select * from education where title like concat( '%',replace('${title}',' ',''), '%') and " +
            " InformationTypes=#{type} limit #{page},#{limit} ")
    public List<Education> E_ListByType(@Param("type") Integer type, @Param("page")Integer page,@Param("limit")Integer limit,@Param("title")String title);
}
