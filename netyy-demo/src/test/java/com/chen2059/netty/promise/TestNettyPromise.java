package com.chen2059.netty.promise;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-09-03
 **/
@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop loop = new NioEventLoopGroup().next();
        DefaultPromise<Integer> promise = new DefaultPromise<>(loop);
        new Thread(() -> {
            log.debug("开始计算");
            try {
                int i = 1/0;
                Thread.sleep(1000);
                promise.setSuccess(80);
            } catch (Exception e) {
                e.printStackTrace();
                promise.setFailure(e);
            }
        }).start();

        log.debug("等待");
        log.debug(String.valueOf(promise.get()));
        log.debug("结束");
    }
}
