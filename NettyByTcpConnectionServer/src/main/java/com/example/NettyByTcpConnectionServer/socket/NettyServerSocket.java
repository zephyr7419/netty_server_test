package com.example.NettyByTcpConnectionServer.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 *  Netty 서버의 시작 및 종료를 관리하는 역할
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class NettyServerSocket {
    private final ServerBootstrap serverBootstrap;
    private final InetSocketAddress tcpPort;
    private Channel serverChannel;

    // ServerBootStrap 을 사용하여 서버를 시작 주입된 tcpPort를 사용하여 지정된 주소의 포트에서 서버를 바인딩
    public void start() {
        try {
            // bind 호출후 sync를 호출하여 작업이 완료 될 때까지 대기 한다.
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();

            // 필드에 바인딩 작업의 결과로 반환된 channel을 저장
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy // 스프링 컨테이너에서 빈이 파괴되기 직전에 호출되는 메서드를 표시
    public void stop() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel.parent().closeFuture();
        }
    }
}
