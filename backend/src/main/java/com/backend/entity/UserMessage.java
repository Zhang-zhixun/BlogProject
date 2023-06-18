package com.backend.entity;

import lombok.Data;

@Data
public class UserMessage {

  private long messageId;
  private User sender;
  private User receiver;
  private String content;
  private long messageStatus;
  private String sendTime;

}
