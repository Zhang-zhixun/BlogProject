package com.backend.controller;

import com.backend.entity.PaymentChannel;
import com.backend.entity.tools.RestBean;
import com.backend.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class PaymentChannelController {

    @Autowired
    PaymentChannelService paymentChannelService;

    @GetMapping("/getPaymentChannelById/{id}")
    public RestBean<PaymentChannel> findPaymentChannelById(@PathVariable int id) {
        return RestBean.success(paymentChannelService.findPaymentChannelById(id));
    }

    @PostMapping("/getPaymentChannelByName")
    public RestBean<List<PaymentChannel>> findPaymentChannelByName(@RequestParam("name") String name) {
        return RestBean.success(paymentChannelService.findPaymentChannelByName(name));
    }

    @PostMapping("/insertPaymentChannel")
    public RestBean<String> insertPaymentChannel(@RequestParam("channelName") String channelName) {
        PaymentChannel paymentChannel = new PaymentChannel();
        paymentChannel.setChannelName(channelName);
        if(paymentChannelService.insertPaymentChannel(paymentChannel)){
            return RestBean.success("添加成功！");
        }else{
            return RestBean.failure(404,"添加失败！");
        }
    }

    @GetMapping("/deletePaymentChannel/{id}")
    public RestBean<String> deletePaymentChannel(@PathVariable int id) {
        if(paymentChannelService.deletePaymentChannel(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }

    @PostMapping("/updatePaymentChannel")
    public RestBean<String> updatePaymentChannel(@RequestParam("channelId") int channelId,
                                                      @RequestParam("channelName") String channelName) {
        PaymentChannel paymentChannel = new PaymentChannel();
        paymentChannel.setChannelId(channelId);
        paymentChannel.setChannelName(channelName);
        if(paymentChannelService.insertPaymentChannel(paymentChannel)){
            return RestBean.success("修改成功！");
        }else{
            return RestBean.failure(404,"修改失败！");
        }
    }

}
