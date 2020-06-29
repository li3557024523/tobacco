package com.xr.base.entity;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TRiskGuard {
    private Integer id;
    private Integer riskId;
    private Date year;
    private Integer dId;
    private Integer pId;
    private String project;
    private String riskDescribe;
    private Integer riskL;
    private Integer riskC;
    private Integer riskD;
    private String riskGrade;
    private String riskGuard;
    private Date createTime;
    private Integer createBy;
    private String createName;
    private Integer state;
    private String mName;
    private String dName;
    private List<SysRole> listRole;
    private List<SysDept> listDept;//

}