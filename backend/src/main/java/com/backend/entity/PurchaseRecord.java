package com.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PurchaseRecord {

  private long recordId;
  private User user;
  private Course course;
  private double cost;
  private Timestamp purchaseTime;
  private PaymentChannel paymentChannel;

}
