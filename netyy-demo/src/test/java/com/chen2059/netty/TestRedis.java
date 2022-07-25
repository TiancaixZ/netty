package com.chen2059.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-01
 */
@Slf4j
public class TestRedis {

    public static void main(String[] args) throws InterruptedException {
        final byte[] LINE = {13, 10};
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(worker);
        bootstrap.handler(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                ByteBuf buffer = ctx.alloc().buffer();
                buffer.writeBytes("*3".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("$3".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("set".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("$4".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("name".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("$8".getBytes());
                buffer.writeBytes(LINE);
                buffer.writeBytes("zhangsan".getBytes());
                buffer.writeBytes(LINE);
                ctx.writeAndFlush(buffer);
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf byteBuf = (ByteBuf) msg;
                System.out.println(byteBuf.toString(Charset.defaultCharset()));
            }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6379).sync();
        channelFuture.channel().closeFuture().sync();
    }

}
