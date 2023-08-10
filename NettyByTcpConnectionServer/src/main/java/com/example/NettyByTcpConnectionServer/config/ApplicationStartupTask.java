package com.example.NettyByTcpConnectionServer.config;

import com.example.NettyByTcpConnectionServer.socket.NettyServerSocket;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *  서버가 실행이 되고 Netty server를 시작하는 역할
 */

@Component
@RequiredArgsConstructor
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {
    // NettyServerSocket 의존성 주입
    private final NettyServerSocket nettyServerSocket;

    // 어플리케이션이 완전히 준비된 후 실행되는 이벤트 핸들러
    // 어플리케이션이 시작될 때 별도의 초기화 작업이 필요한 경우 이런 패턴을 사용하여 가능.
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        nettyServerSocket.start();
    }
}
