package com.chen2059.NIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class Client {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
            System.out.println("waiting");
        } catch (IOException e) {
        }
    }
}
