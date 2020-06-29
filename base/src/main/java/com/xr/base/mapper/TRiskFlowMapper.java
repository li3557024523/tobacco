package com.xr.base.mapper;

import com.xr.base.entity.TRiskFlow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface TRiskFlowMapper {
    /**
     * 查询所有
     * @return
     */
    @Select({
            "<script> ",
            "Select id,risk_id,flowname,flowyear,flowmassge,accessory,create_time,create_name,state from t_risk_flow where 1=1",
            "<when test = 'qyear != null'>",
            " and flowyear = '${qyear}'",
            "</when>",
            "<when test = 'pgname != null'>",
            " and  flowname = '${pgname}'",
            "</when>",
            "</script>" })

    @Results({
            @Result(column ="id",property = "id"),
            @Result(column = "risk_id", property = "riskId"),
            @Result(column = "flowname", property = "flowName"),
            @Result(column = "flowyear", property = "flowYear"),
            @Result(column = "flowmassge", property = "flowMassGe"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_name", property = "createName"),
            @Result(column = "state", property = "state"),
    })
    List<TRiskFlow> list(@Param("pgname") String pgname,@Param("qyear") String qyear);

    /**
     * 删除
     */
    @Delete("delete from t_risk_flow where id = #{id}")
    void delete(int id);
}
