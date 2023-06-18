package com.backend.entity;


import lombok.Data;

@Data
public class LearningProgress {

  private User user;
  private Chapter chapter;
  private CourseResource courseResource;
  private long progress;

}
