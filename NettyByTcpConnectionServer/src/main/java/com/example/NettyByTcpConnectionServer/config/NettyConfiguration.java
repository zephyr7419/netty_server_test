package com.example.NettyByTcpConnectionServer.config;

import com.example.NettyByTcpConnectionServer.socket.NettyChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@RequiredArgsConstructor
public class NettyConfiguration {

    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private int port;
    @Value("${server.netty.boss-count}")
    private int bossCount;
    @Value("${server.netty.worker-count}")
    private int workerCount;
    @Value("${server.netty.keep-alive}")
    private boolean keepAlive;
    @Value("${server.netty.backlog}")
    private int backlog;

    @Bean
    public ServerBootstrap serverBootstrap(NettyChannelInitializer nettyChannelInitializer) {
        // ServerBootstrap : 서버 설정을 도와주는 class
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workerGroup()) // 루프 그룹 설정
                .channel(NioServerSocketChannel.class)    // 비동기 네트워킹을 위한 채널을 설정
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(nettyChannelInitializer);   // 사용자 정의 채널 초기화 클래스 설정
        serverBootstrap.option(ChannelOption.SO_BACKLOG, backlog);

        return serverBootstrap;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount); // 들어오는 연결을 수락하는 역할을 담당함. 거기서 사용 될 스레드 수를 정의
    }

    @Bean(destroyMethod = "shutdownGracefully") // 스프링 컨테이너가 종료될 때 해당 메서드가 호출되도록 한다. 네티의 이벤트 루프가 안전히 종료되게 해줍니다.
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount); // 연결된 클라이언트와의 데이터 통신을 처리하는 역할을 담당 . 거기서 사용될 스레드수를 정의
    }

    @Bean
    public InetSocketAddress tcpPort() {
        // 여기서 포트 번호와 호스트 정보를 생성하여 반환합니다.
        return new InetSocketAddress(host, port); // 적절한 포트 번호와 호스트 정보로 변경해주세요.
    }
}
