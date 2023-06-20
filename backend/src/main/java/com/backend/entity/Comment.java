package com.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {

  private long commentId;
  private User user;
  private Course course;
  private String content;
  private Timestamp publishTime;
  private long likeCount;
  private long isHidden;

}
