package com.example.NettyByTcpConnectionServer.socket;

import com.example.NettyByTcpConnectionServer.decoder.TestDecoder;
import com.example.NettyByTcpConnectionServer.handler.TestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// 새로운 연결이 수립될 때 마다 채널 파이프라인을 초기화 하는 역할
@Component
@RequiredArgsConstructor
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final TestHandler testHandler;

    // 새로운 연결이 들어올 때마다 호출된다.
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 채널 파이프라인을 들고옵니다.
        ChannelPipeline pipeline = ch.pipeline();

        TestDecoder testDecoder = new TestDecoder();
        // StringDecoder 는 바이트 데이터를 UTF-8로 변경합니다. testDecoder는 특정 길이만큼 읽고 문자열로 변환한다. testHandler는 연결관리 및 메시치 저리
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8), testDecoder, testHandler);

    }
}
