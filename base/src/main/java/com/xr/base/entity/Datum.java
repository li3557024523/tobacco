package com.xr.base.entity;


public class Datum {

  private Integer id;
  private String title;
  private String fileName;
  private String fileAddress;
  private java.sql.Date creatDate;
  private Integer creatId;
  private String creatName;
  private Integer state;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getFileAddress() {
    return fileAddress;
  }

  public void setFileAddress(String fileAddress) {
    this.fileAddress = fileAddress;
  }


  public java.sql.Date getCreatDate() {
    return creatDate;
  }

  public void setCreatDate(java.sql.Date creatDate) {
    this.creatDate = creatDate;
  }


  public Integer getCreatId() {
    return creatId;
  }

  public void setCreatId(Integer creatId) {
    this.creatId = creatId;
  }


  public String getCreatName() {
    return creatName;
  }

  public void setCreatName(String creatName) {
    this.creatName = creatName;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
