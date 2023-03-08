package com.ngochung.rabbitmrpublishmessage.controller;

import com.ngochung.rabbitmrpublishmessage.config.RabbitMQConfig;
import com.ngochung.rabbitmrpublishmessage.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
public class RabbitMQWebController {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY, message);

        return "Message Published";
    }
}
