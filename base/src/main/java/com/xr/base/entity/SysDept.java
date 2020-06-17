package com.xr.base.entity;

import lombok.Data;

@Data
public class SysDept {

  private Integer id;
  private String deptName;
  private Integer parentId;
  private Integer orderNum;
  private String principalName;
  private String info;
  private String leadership;
  private java.sql.Time date;
  private Integer createId;
  private String createName;
  private String state;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }


  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }


  public Integer getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(Integer orderNum) {
    this.orderNum = orderNum;
  }


  public String getPrincipalName() {
    return principalName;
  }

  public void setPrincipalName(String principalName) {
    this.principalName = principalName;
  }


  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }


  public String getLeadership() {
    return leadership;
  }

  public void setLeadership(String leadership) {
    this.leadership = leadership;
  }


  public java.sql.Time getDate() {
    return date;
  }

  public void setDate(java.sql.Time date) {
    this.date = date;
  }


  public Integer getCreateId() {
    return createId;
  }

  public void setCreateId(Integer createId) {
    this.createId = createId;
  }


  public String getCreateName() {
    return createName;
  }

  public void setCreateName(String createName) {
    this.createName = createName;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
