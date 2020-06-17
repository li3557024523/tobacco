package com.xr.base.entity;

import lombok.Data;

import java.sql.Time;

@Data
public class SysEmp {

  private Integer id;
  private String name;
  private String sex;
  private Integer age;
  private String education;
  private String politics;
  private String phone;
  private Integer deptId;
  private String username;
  private String password;
  private String salt;
  private Integer roleId;
  private java.sql.Time date;
  private Integer createId;
  private String createName;
  private String state;
  private String Dname;
  private String Rname;

}
