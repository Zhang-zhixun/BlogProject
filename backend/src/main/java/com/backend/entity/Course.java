package com.backend.entity;


import lombok.Data;

@Data
public class Course {

  private long courseId;
  private String courseName;
  private String courseDescription;
  private double coursePrice;
  private Teacher teacher;
  private long onlineStatus;

}
