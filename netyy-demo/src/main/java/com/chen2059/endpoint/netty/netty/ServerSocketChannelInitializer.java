package com.chen2059.endpoint.netty.netty;

import com.chen2059.common.IConnectionManger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.ServerSocketChannel;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-25
 */
public class ServerSocketChannelInitializer extends ChannelInitializer<ServerSocketChannel> {

    private IConnectionManger<Channel> channelIConnectionManger;


    public ServerSocketChannelInitializer(IConnectionManger<Channel> channelIConnectionManger) {
        this.channelIConnectionManger = channelIConnectionManger;
    }

    @Override
    protected void initChannel(ServerSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new NettySessionHandler(channelIConnectionManger));
    }


}
