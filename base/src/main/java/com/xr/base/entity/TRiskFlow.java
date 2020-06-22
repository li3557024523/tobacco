package com.xr.base.entity;

import lombok.Data;

@Data
public class TRiskFlow {
  private Integer id;
  private Integer riskId;
  private String flowName;
  private String flowYear;
  private String flowMassGe;
  private String accessory;
  private Data createTime;
  private String createBy;
  private String createName;
  private String state;
}
