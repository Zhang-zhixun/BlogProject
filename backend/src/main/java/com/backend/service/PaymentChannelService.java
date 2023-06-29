package com.backend.service;

import com.backend.entity.PaymentChannel;

import java.util.List;

public interface PaymentChannelService {

    PaymentChannel findPaymentChannelById(int id);

    List<PaymentChannel> findPaymentChannelByName(String name);

    boolean insertPaymentChannel(PaymentChannel PaymentChannel);

    boolean deletePaymentChannel(int id);

    boolean updatePaymentChannel(PaymentChannel PaymentChannel);

}
