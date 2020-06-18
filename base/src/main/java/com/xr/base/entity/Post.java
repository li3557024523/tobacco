package com.xr.base.entity;


public class Post {

  private Integer id;
  private Integer pid;
  private String pname;
  private Integer did;
  private String postinfo;
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


  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public Integer getDid() {
    return did;
  }

  public void setDid(Integer did) {
    this.did = did;
  }


  public String getPostinfo() {
    return postinfo;
  }

  public void setPostinfo(String postinfo) {
    this.postinfo = postinfo;
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
