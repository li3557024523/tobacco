package com.xr.base.entity;

import lombok.Data;

@Data
public class TEarlyWarning {

  private long id;
  private long headline;
  private String content;
  private java.sql.Timestamp creatTime;
  private String creatBy;
  private String creatName;
  private long state;

}
