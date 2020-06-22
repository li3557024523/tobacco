package com.xr.base.mapper;

import com.xr.base.entity.Literature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface L_mapper {
    @Select("Select * from literature limit #{limit},#{page}")
    public List<Literature> list(Integer page,Integer limit);
}
