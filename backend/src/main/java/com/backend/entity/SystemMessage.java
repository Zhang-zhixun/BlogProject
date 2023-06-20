package com.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SystemMessage {

  private long messageId;
  private String content;
  private long messageStatus;
  private Timestamp sendTime;
  private UserAccount userAccount;

}
