package com.chen2059.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             final FileChannel to = new FileOutputStream("to.txt").getChannel()) {
            long size = from.size();
            for (long left = size; left > 0;){
                left -= from.transferTo((size-left),left,to);
            }
        } catch (IOException e) {
        }
    }
}
