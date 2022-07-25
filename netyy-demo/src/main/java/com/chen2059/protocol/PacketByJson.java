package com.chen2059.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-25
 */
public class PacketByJson extends MessageToMessageCodec<ByteBuf, String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

    }
}
