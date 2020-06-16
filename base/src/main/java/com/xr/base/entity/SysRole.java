package com.xr.base.entity;

import lombok.Data;

@Data
public class SysRole {

  private Integer id;
  private String name;
  private String remark;
  private java.sql.Time createDate;
  private Integer createId;
  private String createName;
  private Integer state;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public java.sql.Time getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Time createDate) {
    this.createDate = createDate;
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


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
