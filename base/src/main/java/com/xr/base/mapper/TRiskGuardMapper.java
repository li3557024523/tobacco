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
    @Insert("INSERT INTO t_risk_guard VALUES(NULL,#{riskId},#{year},#{dId},#{Pid},#{project},#{riskDescribe},#{riskL},#{riskC},#{riskD},#{riskGrade},#{riskGuard},#{createTime},#{createBy},#{createName},#{state})")
    void add(TRiskGuard tRickGuard);
    /**
     * 删除
     */
    @Delete("delete from t_risk_guard where id = #{id}")
    void delete(int id);
    /**
     * 查询单个
     */
    @Select("select * from t_risk_guard where id = #{id}")
    SysEmp findOne(int id);

    /**
     * 查询所以风险
     */
    @Select("Select \n" +
            "tr.id\n" +
            ",tr.risk_id\n" +
            ",tr.`year`\n" +
            ",tr.d_id\n" +
            ",tr.p_id\n" +
            ",tr.project\n" +
            ",tr.risk_describe\n" +
            ",tr.riskL\n" +
            ",tr.riskC\n" +
            ",tr.riskD\n" +
            ",tr.risk_grade\n" +
            ",tr.risk_guard\n" +
            ",tr.create_time\n" +
            ",tr.create_by\n" +
            ",tr.create_name\n" +
            ",tr.state \n" +
            ",d.dept_name as dName\n" +
            ",r.`name` as mName\n" +
            "from \n" +
            "t_risk_guard tr\n" +
            ",sys_dept d\n" +
            ",sys_role r\n" +
            ",t_risk_guard_dept trd\n" +
            ",t_risk_guard_menu trm\n" +
            " where \n" +
            " tr.d_id = trd.dept_id \n" +
            " and d.id = trd.d_id\n" +
            " and tr.p_id = trm.menu_id\n" +
            " and r.id = trm.p_id")
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
    List<TRiskGuard> RickAll();


    @Select("select * from sys_role where state = '启用'")
    List<SysRole> queryRole();

}
