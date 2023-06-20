package com.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Reply {

  private long replyId;
  private User user;
  private Comment comment;
  private String content;
  private Timestamp publishTime;
  private long isHidden;


}
