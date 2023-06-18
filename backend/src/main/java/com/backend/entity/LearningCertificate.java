package com.backend.entity;


import lombok.Data;

@Data
public class LearningCertificate {

  private long certificateId;
  private User user;
  private Course course;
  private CertificateTemplate certificateTemplate;
  private String generateTime;


}
