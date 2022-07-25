package com.chen2059.endpoint.factory.endpoint;

import com.chen2059.business.StorageMessage;
import com.chen2059.common.IConnectionManger;
import com.chen2059.endpoint.IEndPoint;

/**
 * 抽象工厂接口
 *
 * @author 陈国震
 * @date 2022-07-21
 */
public interface AbstractSEndPointFactory<T, V> {

    IEndPoint  createTcpServer(String type, IConnectionManger<T> connectionManger, StorageMessage<V> storageMessage);

    IEndPoint  createTcpClient();

    IEndPoint  createUdpServer(String type);

    IEndPoint  createUdpClient();


}
