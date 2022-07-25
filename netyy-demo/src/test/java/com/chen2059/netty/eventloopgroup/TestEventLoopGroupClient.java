package com.chen2059.netty.eventloopgroup;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-31
 **/
public class TestEventLoopGroupClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                        System.out.println(channel);
                        System.out.println("init.....");
                    }
                })
                .connect("127.0.0.1", 8080)
                .sync()
                .channel();
    }
}
