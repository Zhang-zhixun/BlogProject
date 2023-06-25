package com.backend.entity;


import lombok.Data;

import java.util.List;

@Data
public class Course {

  private long courseId;
  private String courseName;
  private String courseDescription;
  private double coursePrice;
  private List<Teacher> teachers;
  private long onlineStatus;

}
