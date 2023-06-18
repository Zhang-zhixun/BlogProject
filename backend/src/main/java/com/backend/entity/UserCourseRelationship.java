package com.backend.entity;

import lombok.Data;

@Data
public class UserCourseRelationship {

  private long userId;
  private Course course;
  private long favoriteStatus;
  private long purchaseStatus;
  private long learningStatus;
  private long learningProgress;


}
