package com.backend.entity;

import lombok.Data;

@Data
public class PurchaseRecord {

  private long recordId;
  private User user;
  private Course course;
  private double cost;
  private String purchaseTime;
  private PaymentChannel paymentChannel;

}
