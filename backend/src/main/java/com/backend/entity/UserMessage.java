package com.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserMessage {

  private long messageId;
  private User sender;
  private User receiver;
  private String content;
  private long messageStatus;
  private Timestamp sendTime;

}
