package com.backend.entity;

import lombok.Data;

@Data
public class Comment {

  private long commentId;
  private User user;
  private Course course;
  private String content;
  private String publishTime;
  private long likeCount;
  private long isHidden;

}
