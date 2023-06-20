package com.backend.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Favorite {

  private User user;
  private Course course;
  private Timestamp favoriteTime;

}
