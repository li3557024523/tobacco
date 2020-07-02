package com.xr.base.mapper;

import com.xr.base.entity.Supervision;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface S_mapper {
    @Select("select * from supervision limit #{page},#{limit}")
    public List<Supervision> list(Integer page,Integer limit);
    @Insert("insert into supervision(type,title,context,depId,accessory,createTime,createId,createId,state) values(" +
            "#{s.type},#{s.title},#{s.context},#{s.depId},#{s.accessory},#{s.createTime},#{s.createId},#{s.createId},#{s.state})")
    public int Ins(Supervision s);
    @Delete("delete from supervision where id=#{id}")
    public int Del(int id);
    @Update("update  supervision set  type=#{s.type},title=#{s.title},context=#{s.context},depId=#{s.depId},accessory=#{s.accessory},state=#{s.state} " +
            " where id=#{s.id} ")
    public int update(Supervision s);
    @Update("update supervise set state=#{s.state} where depId=#{s.depId}")
    public int updateZT(Supervision s);
    @Update("update supervise set state=#{state} where id=#{id}")
    int  deleteZT(Supervision superviseReport);
}
