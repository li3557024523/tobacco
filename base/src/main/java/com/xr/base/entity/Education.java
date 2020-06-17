package com.xr.base.entity;


public class Education {

  private Integer id;
  private String title;
  private String origin;
  private Integer informationTypes;
  private String context;
  private java.sql.Date pubdate;
  private java.sql.Date addTime;
  private Integer createId;
  private String creator;
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


  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }


  public Integer getInformationTypes() {
    return informationTypes;
  }

  public void setInformationTypes(Integer informationTypes) {
    this.informationTypes = informationTypes;
  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }


  public java.sql.Date getPubdate() {
    return pubdate;
  }

  public void setPubdate(java.sql.Date pubdate) {
    this.pubdate = pubdate;
  }


  public java.sql.Date getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Date addTime) {
    this.addTime = addTime;
  }


  public Integer getCreateId() {
    return createId;
  }

  public void setCreateId(Integer createId) {
    this.createId = createId;
  }


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
