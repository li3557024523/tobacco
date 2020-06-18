package com.xr.base.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysDept {

  private Integer id;
  private String deptName;
  private Integer parentId;
  private Integer did;
  private String principalName;
  private String info;
  private String leadership;
  private Date date;
  private Integer createId;
  private String createName;
  private String state;
  private List<SysDept> DeptCharlen;

}
