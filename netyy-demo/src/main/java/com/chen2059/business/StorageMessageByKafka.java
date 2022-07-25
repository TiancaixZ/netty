package com.chen2059.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-25
 */
@Service
@Slf4j
public class StorageMessageByKafka<V> implements StorageMessage<V> {

    @Resource
    private KafkaTemplate<String,V> kafkaTemplate;


    @Override
    public void saveMessage(V v) {
        kafkaTemplate.send("", v).addCallback(
                new ListenableFutureCallback<SendResult<String, V>>() {
                    @Override
                    public void onFailure(Throwable ex) {

                    }

                    @Override
                    public void onSuccess(SendResult<String, V> result) {

                    }
                }
        );
    }
}
