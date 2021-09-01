package com.chen2059.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-25
 **/
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream(new File("/Users/chenguozhen/Downloads/netty/netyy-demo/src/data.txt")).getChannel();){
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);
                log.debug("读取到的字节",len);
                if (len == -1){
                    break;
                }
                buffer.flip();//读模式
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.debug( "实际字节" + (char)b);
                }
                buffer.clear();//写模式
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
