package com.example.mall.portal.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailRabbitMqConfig {






    //定义imQueue的队列
    @Bean
    public Queue mailQueue() {
        return new Queue("mail.queue");
    }

    //定义名字为x的交换机
    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange("mail.x");
    }


    //绑定队列和交换机 routingKey为topic.#
    @Bean
    Binding bindMail() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with("mailKey");
    }

}
