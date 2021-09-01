package com.chen2059.NIO;

import java.nio.ByteBuffer;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestByeteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

//        buffer.get(new byte[4]);
//        debugAll(buffer);
//        buffer.rewind();
//        System.out.println((char)buffer.get());

//        System.out.println((char)buffer.get());
//        System.out.println((char)buffer.get());
//        buffer.mark();
//        System.out.println((char)buffer.get());
//        System.out.println((char)buffer.get());
//        buffer.reset();
//        System.out.println((char)buffer.get());

        System.out.println(buffer.get(2));
        debugAll(buffer);

    }
}
