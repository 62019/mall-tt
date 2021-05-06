package com.example.mall.portal.config.mqTemp;

import com.example.mall.portal.service.OmsOrderPortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
//绑定的routekey去哪了 难道队列名和这个名必须一致？
@RabbitListener(queues = "mail.queue")
public class MailReceiver {


    @RabbitHandler
    public void handle(Map testMessage){

        log.info("接受邮箱"+testMessage.toString());
    }
}