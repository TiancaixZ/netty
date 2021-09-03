package com.chen2059.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-09-03
 **/
@Slf4j
public class TestJdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 50;
            }
        });

        log.debug(String.valueOf(future.get()));

    }
}
