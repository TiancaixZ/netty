package com.chen2059.netty.eventloopgroup;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-06-30
 */
@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws Exception {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                        System.out.println(ch);
                        System.out.println("init.....");
                    }
                })
                .connect("127.0.0.1", 4444)
                .sync()
                .channel();

        System.out.println(channel);
        System.out.println("");
    }
}
