package com.xr.base.mapper;

import com.xr.base.entity.TEarlyWarning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TEarlyWarningMapper {

    /**
     * 按风险等级查询所有
     * @return
     */
    @Select("select * from t_early_warning ew,t_risk_guard rg WHERE rg.project = ew.content")
    List<TEarlyWarning> list();


}
