package com.chen2059.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-29
 **/
public class WriteServer {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9000));
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT,null);

        while (true){
            selector.select();
            final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()){
                final SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    final SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    final SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ, null);
                    final StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 3000; i++) {
                        stringBuilder.append("a");
                    }
                    final ByteBuffer encode = Charset.defaultCharset().encode(stringBuilder.toString());
                    final int write = socketChannel.write(encode);
                    System.out.println(write);
                    while(encode.hasRemaining()){
                        selectionKey.interestOps(selectionKey.interestOps() + selectionKey.OP_WRITE);
                        selectionKey.attach(encode);
                    }
                } else if (key.isWritable()){
                    final ByteBuffer buffer = (ByteBuffer) key.attachment();
                    final SocketChannel channel = (SocketChannel) key.channel();
                    final int write = channel.write(buffer);
                    System.out.println(write);
                    if(!buffer.hasRemaining()){
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }
}
