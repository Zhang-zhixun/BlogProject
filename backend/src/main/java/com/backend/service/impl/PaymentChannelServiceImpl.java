package com.backend.service.impl;

import com.backend.entity.PaymentChannel;
import com.backend.mapper.PaymentChannelMapper;
import com.backend.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentChannelServiceImpl implements PaymentChannelService {

    @Autowired
    PaymentChannelMapper paymentChannelMapper;

    @Override
    public PaymentChannel findPaymentChannelById(int id) {
        return paymentChannelMapper.findPaymentChannelById(id);
    }

    @Override
    public List<PaymentChannel> findPaymentChannelByName(String name) {
        return paymentChannelMapper.findPaymentChannelByName(name);
    }

    @Override
    public boolean insertPaymentChannel(PaymentChannel PaymentChannel) {
        return paymentChannelMapper.insertPaymentChannel(PaymentChannel);
    }

    @Override
    public boolean deletePaymentChannel(int id) {
        return paymentChannelMapper.deletePaymentChannel(id);
    }

    @Override
    public boolean updatePaymentChannel(PaymentChannel PaymentChannel) {
        return paymentChannelMapper.updatePaymentChannel(PaymentChannel);
    }
}
