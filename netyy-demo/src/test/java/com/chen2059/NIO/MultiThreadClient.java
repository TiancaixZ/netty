package com.chen2059.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-31
 **/
public class MultiThreadClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, 0, null);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isConnectable()) {
                    System.out.println("connectd");
                } else if(selectionKey.isWritable()) {

                }
            }
        }
    }
}
