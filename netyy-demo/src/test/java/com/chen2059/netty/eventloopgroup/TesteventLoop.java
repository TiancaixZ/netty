package com.chen2059.netty.eventloopgroup;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-09-01
 **/
@Slf4j
public class TesteventLoop {
    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        System.out.println(eventLoopGroup.next());
        System.out.println(eventLoopGroup.next());
        System.out.println(eventLoopGroup.next());
        System.out.println(eventLoopGroup.next());

        eventLoopGroup.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("thread");
        });

        log.debug("main");

        eventLoopGroup.next().scheduleAtFixedRate(() -> {
            System.out.println("ok");
        }, 0,1, TimeUnit.SECONDS);
    }
}
