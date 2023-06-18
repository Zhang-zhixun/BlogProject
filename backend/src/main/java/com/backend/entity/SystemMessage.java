package com.backend.entity;

import lombok.Data;

@Data
public class SystemMessage {

  private long messageId;
  private String content;
  private long messageStatus;
  private String sendTime;
  private UserAccount userAccount;

}
