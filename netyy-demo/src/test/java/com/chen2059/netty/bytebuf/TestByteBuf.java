package com.chen2059.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * byteBuf
 *
 * @author 陈国震
 * @date 2022-07-01
 */
public class TestByteBuf {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            builder.append("a");
        }
        buffer.writeBytes(builder.toString().getBytes());
        System.out.println(buffer);
    }
}
