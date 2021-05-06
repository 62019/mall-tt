package com.example.mall.portal.config.mqTemp;

import com.example.mall.portal.service.OmsOrderPortalService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Slf4j
//绑定的routekey去哪了 难道队列名和这个名必须一致？
@RabbitListener(queues = "imQueue")
public class Receiver {
    @Autowired
    private OmsOrderPortalService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        System.out.println("什么鬼");
//        portalOrderService.cancelOrder(orderId);

        portalOrderService.cancelOrder(orderId);


        log.info("process orderId:{}",orderId);
    }
}