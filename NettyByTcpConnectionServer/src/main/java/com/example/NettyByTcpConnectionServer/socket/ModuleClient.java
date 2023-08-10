package com.example.NettyByTcpConnectionServer.socket;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

//@Component
public class ModuleClient {
    public Mono<Connection> connectToModule() {
        TcpClient client = TcpClient.create()
                .host("localhost")
                .port(8888);

        return client.connect().cast(Connection.class);
    }

    public Mono<Void> sendDataToModule(Connection connection, byte[] data) {
        return connection.outbound().sendObject(Mono.just(data))
                .then();
    }

}
