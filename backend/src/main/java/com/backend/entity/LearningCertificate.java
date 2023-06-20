package com.backend.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class LearningCertificate {

  private long certificateId;
  private User user;
  private Course course;
  private CertificateTemplate certificateTemplate;
  private Timestamp generateTime;


}
