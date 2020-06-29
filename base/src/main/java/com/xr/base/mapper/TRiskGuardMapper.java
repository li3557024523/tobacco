package com.xr.base.mapper;

import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysRole;
import com.xr.base.entity.TRiskGuard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@Mapper
public interface TRiskGuardMapper {

    /**
     * 增加
     */
    @Insert("INSERT INTO t_risk_guard VALUES(\n" +
            "NULL,\n" +
            "#{t.riskId} \n" +
            ",NOW()\n" +
            ",#{t.dId}\n" +
            ",#{t.pId}\n" +
            ",#{t.project}\n" +
            ",#{t.riskDescribe}\n" +
            ",#{t.riskL}\n" +
            ",#{t.riskC}\n" +
            ",#{t.riskD}\n" +
            ",#{t.riskGrade}\n" +
            ",#{t.riskGuard}\n" +
            ",NOW()\n" +
            ",#{t.createBy}\n" +
            ",#{t.createName}\n" +
            ",1)")
    void add(@Param("t") TRiskGuard tRickGuard);
    /**
     * 删除
     */
    @Delete("delete from t_risk_guard where id = #{id}")
    void delete(int id);

    /**
     * 查询所以风险
     */
    @Select({"<script> ",
            "Select tr.id,tr.risk_id,tr.`year`,tr.d_id,tr.p_id,tr.project,tr.risk_describe,tr.riskL,tr.riskC,tr.riskD,tr.risk_grade,tr.risk_guard,tr.create_time,tr.create_by,tr.create_name,tr.state,d.dept_name as dName,r.`name` as mName from t_risk_guard tr,sys_dept d,sys_role r,t_risk_guard_dept trd,t_risk_guard_menu trm",
            " where ",
            " tr.d_id = trd.dept_id ",
            " and d.id = trd.d_id",
            " and tr.p_id = trm.menu_id",
            " and r.id = trm.p_id ",
            "<when test = 'p!=null'>",
            " and tr.p_id = '${p}'",
            "</when>",
            "<when test = 'd!=null'>",
            " and  tr.d_id = '${d}'",
            "</when>",
            "</script>" })
    @Results({
            @Result(column ="id",property = "id"),
            @Result(column = "risk_id", property = "riskId"),
            @Result(column = "year", property = "year"),
            @Result(column = "d_id", property = "dId"),
            @Result(column = "p_id", property = "pId"),
            @Result(column = "project", property = "project"),
            @Result(column = "risk_describe", property = "riskDescribe"),
            @Result(column = "riskL", property = "riskL"),
            @Result(column = "riskC", property = "riskC"),
            @Result(column = "riskD", property = "riskD"),
            @Result(column = "risk_grade", property = "riskGrade"),
            @Result(column = "risk_guard", property = "riskGuard"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_by", property = "createBy"),
            @Result(column = "state", property = "state"),
            @Result(column = "mName", property = "mName"),
            @Result(column = "dName", property = "dName"),
    })
    List<TRiskGuard> RickAll(@Param("p") Integer pid,@Param("d") Integer did);


    /**
     * 条件查询
     */
    @Select("Select * from t_risk_guard where dId = #{dId} ")
    List<TRiskGuard> listWhere(int did);






}
