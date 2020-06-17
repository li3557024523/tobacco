package com.xr.base.mapper;

import com.xr.base.entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface E_mapper {
    @Select("select * from education ")
    public List<Education> E_List();
    @Select("select * from education where title like '%#{title}%'  ")
    public Education E_SelByTitle(String title);
    @Insert("insert into education(title,origin,informationTypes,context,pubdate,addTime,createId,creator,state) values(#{e.title" +
            "},#{e.origin},#{e.informationTypes},#{e.context},#{e.pubdate},#{e.addTime},#{e.createId},#{e.creator},#{e.state}) ")
    public int E_Ins(Education e);

    @Update("update  education set  (title,origin,informationTypes,context,pubdate,addTime,createId,creator,state) =(#{e.title" +
            "},#{e.origin},#{e.informationTypes},#{e.context},#{e.pubdate},#{e.addTime},#{e.createId},#{e.creator},#{e.state}) where id=#{e.id} ")
    public int E_Upd(Education e);
    @Select("delete from education where id=#{id}")
    public int E_Del(int id);
    @Select("select * from education where InformationTypes=#{type} limit #{page},#{limit} ")
    public List<Education> E_ListByType(@Param("type") Integer type, @Param("page")Integer page,@Param("limit")Integer limit);
}
