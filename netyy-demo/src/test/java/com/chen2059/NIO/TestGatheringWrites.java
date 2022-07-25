package com.chen2059.NIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestGatheringWrites {
    public static void main(String[] args) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("你好");

        try (FileChannel channel = new RandomAccessFile(new File("data.txt"), "rw").getChannel()) {
            channel.write(new ByteBuffer[]{buffer, buffer1, buffer2});
        } catch (IOException e) {
        }
    }
}
