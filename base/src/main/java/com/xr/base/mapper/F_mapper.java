package com.xr.base.mapper;

import com.xr.base.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface F_mapper {
    @Select("select * from feedback where feedbackType =#{feedbackType} limit #{page},#{limit}")
    public List<Feedback> list(Integer feedbackType,Integer page,Integer limit);
    @Update("update feedback set ")
    public int upd(Feedback f);

}
