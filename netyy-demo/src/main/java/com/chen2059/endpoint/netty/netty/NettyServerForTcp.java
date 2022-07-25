package com.chen2059.endpoint.netty.netty;

import com.chen2059.business.StorageMessage;
import com.chen2059.common.IConnectionManger;
import com.chen2059.endpoint.AbstractEndPointForServer;
import com.chen2059.endpoint.IEndPoint;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-20
 */
@Slf4j
public class NettyServerForTcp extends AbstractEndPointForServer {

    private  NioEventLoopGroup BOSS_GROUP;

    private  NioEventLoopGroup WORKER_GROUP;

    private ServerBootstrap serverBootstrap;

    private ChannelFuture channelFuture;

    public NettyServerForTcp() {
    }

    public NettyServerForTcp(IConnectionManger connectionManger) {
        super(connectionManger);
    }


    public NettyServerForTcp(int port, IConnectionManger connectionManger) {
        super(port, connectionManger);
    }

    public NettyServerForTcp(IConnectionManger connectionManger, StorageMessage storageMessage) {
        super(connectionManger, storageMessage);
    }

    public NettyServerForTcp(int port, IConnectionManger connectionManger, StorageMessage storageMessage) {
        super(port, connectionManger, storageMessage);
    }

    @Override
    public IEndPoint init() {
        serverBootstrap = new ServerBootstrap();
        BOSS_GROUP = new NioEventLoopGroup(1);
        WORKER_GROUP = new NioEventLoopGroup();
        serverBootstrap.group(BOSS_GROUP, WORKER_GROUP)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                /*对serversocketchannel的回调*/
                .childHandler(new ServerSocketChannelInitializer(super.connectionManger))
                /*对socketchannel的回调*/
                .childHandler(new ChannelHandlerInitializer(super.storageMessage));
        return this;
    }


    @Override
    public void start() throws Exception {
        channelFuture = serverBootstrap.bind(port).sync();
    }



    @Override
    public void stop() {

    }

    @Override
    public void destory() throws Exception{
        if (channelFuture == null) {
            throw new Exception("channelFuture is null");
        }
        channelFuture.channel().closeFuture().addListener(new ChannelFutureListener()
        {
            @Override public void operationComplete(ChannelFuture future) throws Exception
            {       //通过回调只关闭自己监听的channel
                future.channel().close();
            }
        });
    }

}
