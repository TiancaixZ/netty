package com.chen2059.endpoint.netty.netty;

import com.chen2059.common.ConnectionSession;
import com.chen2059.common.IConnectionManger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty connectsession
 *
 * @author 陈国震
 * @date 2022-07-25
 */
@ChannelHandler.Sharable
public class NettySessionHandler extends ChannelInboundHandlerAdapter {

    private IConnectionManger<Channel> channelIConnectionManger;

    public NettySessionHandler(IConnectionManger<Channel> channelIConnectionManger) {
        this.channelIConnectionManger = channelIConnectionManger;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ConnectionSession<Channel> channelConnectionSession = new ConnectionSession<>();
        channelIConnectionManger.addChannel(channelConnectionSession,ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
