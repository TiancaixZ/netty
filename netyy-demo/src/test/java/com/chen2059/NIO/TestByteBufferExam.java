package com.chen2059.NIO;

import java.nio.ByteBuffer;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestByteBufferExam {
    public static void main(String[] args) {
        final ByteBuffer allocate = ByteBuffer.allocate(32);
        allocate.put("hello,world\nI'm zhangsan\nHo".getBytes());
        split(allocate);
        allocate.put("w are you?\n".getBytes());
        split(allocate);
    }

    private static void split(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n'){
                int length = i + 1 - buffer.position();
                final ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(buffer.get());
                }
                debugAll(target);
            }
        }
        buffer.compact();
    }
}
