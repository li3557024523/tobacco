package com.xr.base.mapper;

import com.xr.base.entity.TRiskFlow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TRiskFlowMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from t_risk_flow")
    List<TRiskFlow> list();

    /**
     * 删除
     */
    @Delete("delete from t_risk_flow where id = #{id}")
    void delete(int id);
}
