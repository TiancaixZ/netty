package com.chen2059.business;

/**
 * 存储
 *
 * @author 陈国震
 * @date 2022-07-25
 */
public interface StorageMessage<V> {

    /**
     * 保存消息
     * @param v 消息
     */
    void saveMessage(V v);
}
