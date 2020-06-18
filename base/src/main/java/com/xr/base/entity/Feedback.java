package com.xr.base.entity;


public class Feedback {

  private Integer id;
  private Integer type;
  private Integer feedbackType;
  private String feedbackContext;
  private Integer depId;
  private String accessory;
  private java.sql.Date createTime;
  private Integer createId;
  private String creator;
  private Integer state;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public Integer getFeedbackType() {
    return feedbackType;
  }

  public void setFeedbackType(Integer feedbackType) {
    this.feedbackType = feedbackType;
  }


  public String getFeedbackContext() {
    return feedbackContext;
  }

  public void setFeedbackContext(String feedbackContext) {
    this.feedbackContext = feedbackContext;
  }


  public Integer getDepId() {
    return depId;
  }

  public void setDepId(Integer depId) {
    this.depId = depId;
  }


  public String getAccessory() {
    return accessory;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }


  public java.sql.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Date createTime) {
    this.createTime = createTime;
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
