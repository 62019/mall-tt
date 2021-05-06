package com.example.mall.portal.config.mqTemp;

import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.service.MiaoshaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
//绑定的routekey去哪了 难道队列名和这个名必须一致？
@RabbitListener(queues = "miaosha.queue")
public class MiaoshaReceiver {

    @Autowired
    MiaoshaService miaoshaService;

    @RabbitHandler
    //@Async
    public void handle(Map testMessage){

        log.info("接受订单"+testMessage.toString());

        Long goodid = (Long) testMessage.get("goodid");
        UmsMember member = (UmsMember) testMessage.get("member");


        //1.判断库存
        // 这还判断啥 redis已经判断了

        //2.判断是否已经下带

        //减库存 创建订单

        miaoshaService.go(goodid,member);


    }
}