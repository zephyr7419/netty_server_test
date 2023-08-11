package com.example.NettyByTcpConnectionServer.mqtt.binder;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

public class MqttSubscriber {

    @Bean
    public Consumer<Message<String>> inbound() {
        return stringMessage -> {
            System.out.println("Received: " + stringMessage.getPayload());
        };
    }

}
