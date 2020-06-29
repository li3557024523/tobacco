package com.xr.base.entity;


import lombok.Data;

import java.util.Date;

@Data
public class SysLog {

  private Integer id;
  private String name;
  private String url;
  private String operation;
  private String ip;
  private String serverip;
  private String operationData;
  private String client;
  private String Browser;
  private Date date;


}
