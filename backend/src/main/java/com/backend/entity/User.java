package com.backend.entity;

import lombok.Data;

@Data
public class User {

  private long userId;
  private String name;
  private String gender;
  private long age;
  private String school;
  private String email;
  private String identity;
  private UserAccount userAccount;



}
