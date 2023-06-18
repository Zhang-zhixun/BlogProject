package com.backend.entity;

import lombok.Data;

@Data
public class CommentLikes {

  private long commentLikesId;
  private UserAccount userAccount;
  private Comment comment;
  private long likeStatus;

}
