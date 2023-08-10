package com.example.NettyByTcpConnectionServer.handler;

import com.example.NettyByTcpConnectionServer.service.ChatService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  Netty server 의 핵심부분 클라이언트와의 연결 수립, 데이터 읽기 및 쓰기 , 예외처리등의 로직이 담겨있다.
 */

@Slf4j
@Component
@ChannelHandler.Sharable // 여러 채널에서 핸들러 인스턴스를 공유할 수 있음을 나타낸다.
public class TestHandler extends ChannelInboundHandlerAdapter {

    private final ChatService chatService;

    public TestHandler(ChatService chatService) {
        this.chatService = chatService;
    }

    // 클라이언트가 서버에 연결 될 때 호출된다.
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        chatService.addClient(ctx); // 해당 서비스에 클라이언트를 등록 로그에 연결정보를 출력
        log.info("Client connected: " + ctx.channel().remoteAddress());
    }

    // 클라이언트가 서버에서 연결이 종료될 때 호출이 됩니다.
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        chatService.removeClient(ctx); // 클라이언트를 서비스에서 제거하고 로그에 연결해제 정보 출력
        log.info("Client disconnected: " + ctx.channel().remoteAddress());
    }

    // 클라이언트로부터 메세지를 읽을 때 호출이 된다.
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 현재 아래의 코드는 object 인 메세지를 String 타입으로 변환하고
        // sendRequestToClient 메서드를 호출하여 메세지를 보낸 클라이언트에게 답을 하는 형식입니다.
        log.info("channelRead called");
        try {
            String receivedMessage = (String) msg;
            chatService.sendRequestToClient(ctx, receivedMessage);
            log.info("received message: {}", receivedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 채널에서 예외가 발생할 때 호출이된다.
    // 클라이언트와의 연결을 닫아버리고 예외정보를 출력합니다.
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}

