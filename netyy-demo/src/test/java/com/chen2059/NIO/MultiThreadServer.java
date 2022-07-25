package com.chen2059.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.chen2059.NIO.ByteBufferUtil.debugAll;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-31
 **/
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boos");
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, null);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(i);
        }
        AtomicInteger index = new AtomicInteger(0);
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
                    log.debug("connected....{}", accept.getRemoteAddress());
                    log.debug("before", accept.getRemoteAddress());
                    workers[index.getAndIncrement() %workers.length].register(accept);
                    log.debug("after", accept.getRemoteAddress());
                }
            }
        }
    }
}

@Slf4j
class Worker implements Runnable {
    private Selector selector;
    private volatile boolean start = false;
    private int index;

    private ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();

    public Worker(int index) {
        this.index = index;
    }

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

    public void register(SocketChannel channel) throws IOException {
        if (!start) {
            selector = Selector.open();
            new Thread(this, "worker-" + index).start();
            start = true;
        }
        tasks.add(() -> {
            try {
                final ByteBuffer buffer = ByteBuffer.allocate(1);
                SelectionKey register = channel.register(selector, 0, buffer);
                register.interestOps(SelectionKey.OP_READ);
                selector.selectNow();
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        selector.wakeup();
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                Runnable poll = tasks.poll();
                if (poll != null) {
                    poll.run();
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                if (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        final ByteBuffer buffer = (ByteBuffer) key.attachment();
                        try {
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
                        } catch (IOException e) {
                            e.printStackTrace();
                            key.cancel();
                            channel.close();
                        }
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
