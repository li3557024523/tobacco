package com.xr.base.mapper;

import com.xr.base.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysDeptMapper {

    @Select("SELECT * FROM sys_dept")
    List<SysDept> groupDept();
}
