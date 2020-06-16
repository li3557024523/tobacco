package com.xr.base.entity;

import lombok.Data;

@Data
public class SysEmp {

  private Integer id;
  private String name;
  private Integer sex;
  private Integer age;
  private String education;
  private Integer politics;
  private String phone;
  private Integer deptId;
  private String username;
  private String password;
  private Integer roleId;
  private java.sql.Time date;
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


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }


  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }


  public Integer getPolitics() {
    return politics;
  }

  public void setPolitics(Integer politics) {
    this.politics = politics;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
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


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
