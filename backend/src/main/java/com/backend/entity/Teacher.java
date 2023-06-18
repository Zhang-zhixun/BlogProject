package com.backend.entity;

import lombok.Data;

@Data
public class Teacher {

  private long teacherId;
  private String name;
  private String gender;
  private long age;
  private UserAccount userAccount;


}
