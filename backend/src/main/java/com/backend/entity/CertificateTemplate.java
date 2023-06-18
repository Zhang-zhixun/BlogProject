package com.backend.entity;


import lombok.Data;

@Data
public class CertificateTemplate {

  private long templateId;
  private String templateName;
  private String templateContent;

}
