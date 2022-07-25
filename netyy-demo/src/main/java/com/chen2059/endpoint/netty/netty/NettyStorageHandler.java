package com.chen2059.endpoint.netty.netty;

import com.chen2059.business.StorageMessage;
import io.netty.channel.*;
import org.springframework.stereotype.Service;

import java.net.SocketAddress;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-25
 */
@Service
@ChannelHandler.Sharable
public class NettyStorageHandler extends ChannelOutboundHandlerAdapter {

    private StorageMessage<String> storageMessage;

    public NettyStorageHandler() {
    }

    public NettyStorageHandler(StorageMessage<String> storageMessage) {
        this.storageMessage = storageMessage;
    }

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.bind(ctx, localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.disconnect(ctx, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.deregister(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
    }
}
