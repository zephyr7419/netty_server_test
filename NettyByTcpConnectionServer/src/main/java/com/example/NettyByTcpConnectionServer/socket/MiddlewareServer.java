package com.example.NettyByTcpConnectionServer.socket;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpServer;

import java.sql.Connection;

//@Component
public class MiddlewareServer {

    public Mono<? extends Connection> startServer() {
        TcpServer server = TcpServer.create()
                .host("localhost")
                .port(8888)
                .handle(((nettyInbound, nettyOutbound) -> {
                    return nettyInbound.receive()
                            .asByteArray()
                            .doOnNext(this::processData).then();
                }));
        return server.bind().cast(Connection.class);
    }

    private void processData(byte[] data) {

    }
}
