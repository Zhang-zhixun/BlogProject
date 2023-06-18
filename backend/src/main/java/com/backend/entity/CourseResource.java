package com.backend.entity;


import lombok.Data;

@Data
public class CourseResource {

  private long resourceId;
  private Chapter chapter;
  private String resourceType;
  private String resourceUrl;
  private long sortOrder;
}
