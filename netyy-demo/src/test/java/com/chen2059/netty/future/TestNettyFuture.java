package com.chen2059.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-09-03
 **/
@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop loop = group.next();
        Future<Integer> future = loop.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                log.debug("等待结果");
                Thread.sleep(1000);
                return 70;
            }
        });

//        log.debug(String.valueOf(future.get()));
//        log.debug("获取结果");
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug(String.valueOf(future.getNow()));
                log.debug("获取结果");
            }
        });
    }
}
