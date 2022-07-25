package com.chen2059.NIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestGatheringReads {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile(new File("/Users/chenguozhen/Downloads/netty/netyy-demo/src/data.txt"), "r").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(3);
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(4);
            channel.read(new ByteBuffer[]{buffer, buffer1, buffer2});
            buffer.flip();
            buffer1.flip();
            buffer2.flip();
            debugAll(buffer);
            debugAll(buffer1);
            debugAll(buffer2);
        } catch (IOException e) {
        }
    }
}
