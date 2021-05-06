package com.example.mall.portal.config.mqTemp;


import com.example.mall.portal.entity.UmsMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MiaoshaSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMiaosha(Map map){
        //给延迟队列发送消息

        amqpTemplate.convertAndSend("miaosha.x","miaoshaKey",map);
        log.info("[发送订单]"+map);
    }
}
