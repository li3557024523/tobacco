package com.xr.base.entity;


public class Department {

  private Integer id;
  private Integer did;
  private String dname;
  private String pname;
  private String info;
  private String leadership;
  private java.sql.Timestamp data;
  private Integer cid;
  private String cname;
  private Integer state;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getDid() {
    return did;
  }

  public void setDid(Integer did) {
    this.did = did;
  }


  public String getDname() {
    return dname;
  }

  public void setDname(String dname) {
    this.dname = dname;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
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


  public java.sql.Timestamp getData() {
    return data;
  }

  public void setData(java.sql.Timestamp data) {
    this.data = data;
  }


  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }


  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
