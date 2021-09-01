package com.chen2059.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-29
 **/
public class WriteClient {
    public static void main(String[] args) throws IOException {
        final SocketChannel socketChannel = SocketChannel.open();
        final Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        socketChannel.connect(new InetSocketAddress("localhost",9000));

        int count = 0;
        while(true){
            selector.select();
            final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                final SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isConnectable()){
                    System.out.println(socketChannel.finishConnect());
                } else if(key.isReadable()){
                    final ByteBuffer buffer = ByteBuffer.allocate(10);
                    count += socketChannel.read(buffer);
                    System.out.println(count);
                    buffer.clear();
                }
            }

        }
    }
}
