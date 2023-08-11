package com.example.NettyByTcpConnectionServer.mqtt.binder;


import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

public class MqttPublisher {
   @Bean
    public Supplier<Message<String>> outbound() {
       return () -> {
           return MessageBuilder.withPayload("message").build();
       };
   }
}
