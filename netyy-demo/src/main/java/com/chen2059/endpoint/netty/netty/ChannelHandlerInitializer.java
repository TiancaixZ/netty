package com.chen2059.endpoint.netty.netty;

import com.chen2059.business.StorageMessage;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  channel 初始化
 *
 * @author 陈国震
 * @date 2022-07-17
 */
@Service
@Slf4j
public class ChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {

    //TODO 放入jar
    private final static Map<String, Map<String, ChannelHandler>> STRATEGY_MAP = new HashMap<>();

    private StorageMessage storageMessage;

    public ChannelHandlerInitializer() {
    }

    public ChannelHandlerInitializer(StorageMessage storageMessage) {
        this.storageMessage = storageMessage;
    }

    static {
//        STRATEGY_MAP.put("/127.0.0.1", channelHandlers);
//        STRATEGY_MAP.put("/172.16.110.86", channelHandlers);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        InetSocketAddress inetSocketAddress = socketChannel.remoteAddress();
        InetAddress address = inetSocketAddress.getAddress();
        int port = inetSocketAddress.getPort();
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("session", new NettyStorageHandler(storageMessage));
        Map<String, ChannelHandler> channelHandlerMap = STRATEGY_MAP.get(address.toString());
        if (MapUtils.isEmpty(channelHandlerMap)) {
            log.error("<> ip ,{}", address.toString());
            throw new NullPointerException("ChannelHandlerStrategy is null");
        }
        channelHandlerMap.forEach((name, handler) -> {
            pipeline.addLast(name, handler);
        });

    }



}
