package com.chen2059.NIO;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
@Slf4j
public class Server {

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

    public static void main(String[] args) {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
//            ByteBuffer buffer = ByteBuffer.allocate(10);
            final Selector selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9000));
            final SelectionKey selectionKey = serverSocketChannel.register(selector, 0, null);
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);

//            ArrayList<SocketChannel> list = new ArrayList<SocketChannel>();
            while(true){
                selector.select();
                final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    final SelectionKey key = iterator.next();
                    iterator.remove();
                    log.debug("key: {}", key);
                    if (key.isAcceptable()) {
                        final ServerSocketChannel scc = (ServerSocketChannel) key.channel();
                        final SocketChannel channel = scc.accept();
                        channel.configureBlocking(false);
                        final ByteBuffer buffer = ByteBuffer.allocate(16);
                        final SelectionKey key1 = channel.register(selector, 0, buffer);
                        key1.interestOps(SelectionKey.OP_READ);
                        log.debug("{}",channel);
                        log.debug("scKey:{}", key1);
                    } else if (key.isReadable()){
                        try {
                            final SocketChannel channel = (SocketChannel) key.channel();
                            final ByteBuffer buffer = (ByteBuffer) key.attachment();
                            final int read = channel.read(buffer);
                            if (read == -1){
                                channel.close();
                            } else {
                                split(buffer);
                                if(buffer.limit() == buffer.position()){
                                    final ByteBuffer buffer1 = ByteBuffer.allocate(buffer.capacity() * 2);
                                    buffer1.flip();
                                    buffer1.put(buffer);
                                    key.attach(buffer);
                                }
                            }
                            buffer.flip();
                            debugAll(buffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                            key.channel();
                        }
                    }
//                    log.debug("key:" + key);
//                    key.channel();
                }
//                final SocketChannel channel = socketChannel.accept();
//                if(channel != null){
//                    log.debug("conected " + channel);
//                    channel.configureBlocking(false);
//                    list.add(channel);
//                }
//                for (SocketChannel c : list) {
//                    final int read = c.read(buffer);
//                    if (read > 0){
//                        buffer.flip();
//                        debugAll(buffer);
//                        buffer.clear();
//                    }
//                }

            }
        } catch (IOException e) {
        }
    }
}
