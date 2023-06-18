package com.backend.entity;


import lombok.Data;

@Data
public class Chapter {

  private long chapterId;
  private Course course;
  private String chapterTitle;

}
