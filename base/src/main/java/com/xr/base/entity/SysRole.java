package com.xr.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {

  private Integer id;
  private String name;
  private String remark;
  private Date createDate;
  private Integer createId;
  private String createName;
  private String state;
  private Integer did;
  private String deptName;


}
