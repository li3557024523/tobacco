package com.xr.base.entity;

import lombok.Data;

@Data
public class SysMenu {

  private Integer id;
  private String name;
  private Integer parentId;
  private String url;
  private String perms;
  private Integer type;


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


  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

}
