package com.backend.entity;


import lombok.Data;

import java.util.List;

@Data
public class Favorite {

  private User user;
  private Course course;
  private String favoriteTime;

}
