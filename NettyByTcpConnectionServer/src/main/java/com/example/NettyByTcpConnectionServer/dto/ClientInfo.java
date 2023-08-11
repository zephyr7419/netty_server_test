package com.example.NettyByTcpConnectionServer.dto;

import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientInfo {
    private ChannelHandlerContext ctx;
    private String deviceId;
    private int order;
}
