package com.example.mall.portal.config.mqTemp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class MailSender {

    @Autowired
    AmqpTemplate amqpTemplate;


    public void sendMail(){
        //给延迟队列发送消息
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "【这是邮箱】";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);

        amqpTemplate.convertAndSend("mail.x","mailKey",map);

        log.info("[sendmail]");
    }
}
