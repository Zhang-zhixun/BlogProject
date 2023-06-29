package com.backend.mapper;

import com.backend.entity.PaymentChannel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentChannelMapper {

    @Select("select * from blog.payment_channel where channel_id = #{id}")
    PaymentChannel findPaymentChannelById(int id);

    @Select("call getByNamePaymentChannel(#{name})")
    List<PaymentChannel> findPaymentChannelByName(String name);

    @Insert("""
            insert into blog.payment_channel (channel_name) VALUES
            (#{channelName})
            """)
    boolean insertPaymentChannel(PaymentChannel PaymentChannel);

    @Delete("delete from blog.payment_channel where channel_id = #{id}")
    boolean deletePaymentChannel(int id);

    @Update("""
            update blog.payment_channel set channel_name=#{channelName}
            where channel_id=#{channelId}
            """)
    boolean updatePaymentChannel(PaymentChannel PaymentChannel);

}
