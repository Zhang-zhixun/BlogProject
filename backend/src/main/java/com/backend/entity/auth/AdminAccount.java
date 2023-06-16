package com.backend.entity.auth;


import lombok.Data;

@Data
public class AdminAccount {

  private long adminAccountId;
  private String email;
  private String adminUsername;
  private String password;

}
