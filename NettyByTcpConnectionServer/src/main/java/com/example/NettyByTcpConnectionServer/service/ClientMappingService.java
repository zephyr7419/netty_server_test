package com.example.NettyByTcpConnectionServer.service;


import com.example.NettyByTcpConnectionServer.dto.ClientInfo;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientMappingService {
    private final Map<String, ClientInfo> clientInfoMap = new ConcurrentHashMap<>();
    private int currentDeviceId = 0;

    public void addClient(ChannelHandlerContext ctx) {
        if (currentDeviceId < 13) {
            String deviceId = String.format("V%02d", ++currentDeviceId);
            clientInfoMap.put(deviceId, new ClientInfo(ctx, deviceId, clientInfoMap.size() + 1));
            log.info("deviceId: {}, clientInfo: {}", deviceId ,clientInfoMap.get(deviceId).getCtx());
        }
    }

    public void removeClient(String deviceId) {
        clientInfoMap.remove(deviceId);
    }

    public ClientInfo getClientById(String deviceId) {
        return clientInfoMap.get(deviceId);
    }
}
