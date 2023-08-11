package com.example.NettyByTcpConnectionServer.service;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class MqttService {

    @Bean
    public Consumer<Message<String>> input() {
        return stringMessage -> {
            System.out.println("Received from MQTT: " + stringMessage.getPayload());
        };
    }

    @Bean
    public Supplier<Message<String>> output() {
        return () -> {
            return MessageBuilder.withPayload("Message to MQTT").build();
        };
    }
}
