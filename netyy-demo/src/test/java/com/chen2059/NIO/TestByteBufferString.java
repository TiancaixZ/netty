package com.chen2059.NIO;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestByteBufferString {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer1);

        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer2);

        String string = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(string);

        buffer1.flip();
        String string1 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(string1);
    }
}
