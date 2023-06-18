package com.backend.entity;

import lombok.Data;

@Data
public class Reply {

  private long replyId;
  private User user;
  private Comment comment;
  private String content;
  private String publishTime;
  private long isHidden;


}
