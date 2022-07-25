package com.chen2059.endpoint;

import com.chen2059.business.StorageMessage;
import com.chen2059.common.ConnectionSession;
import com.chen2059.common.IConnectionManger;

import java.util.List;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-21
 */
public abstract class AbstractEndPointForServer<T,V> implements IEndPoint {

    protected int port = 9999;

    protected IConnectionManger<T> connectionManger;

    protected StorageMessage<V> storageMessage;

    public AbstractEndPointForServer() {
    }

    public AbstractEndPointForServer(IConnectionManger<T> connectionManger) {
        this.connectionManger = connectionManger;
    }

    public AbstractEndPointForServer(int port, IConnectionManger<T> connectionManger) {
        this.port = port;
        this.connectionManger = connectionManger;
    }

    public AbstractEndPointForServer(IConnectionManger<T> connectionManger, StorageMessage<V> storageMessage) {
        this.connectionManger = connectionManger;
        this.storageMessage = storageMessage;
    }

    public AbstractEndPointForServer(int port, IConnectionManger<T> connectionManger, StorageMessage<V> storageMessage) {
        this.port = port;
        this.connectionManger = connectionManger;
        this.storageMessage = storageMessage;
    }



    public IConnectionManger<T> getConnectionManger() {
        return connectionManger;
    }

    public void setConnectionManger(IConnectionManger<T> connectionManger) {
        this.connectionManger = connectionManger;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public StorageMessage<V> getStorageMessage() {
        return storageMessage;
    }

    public void setStorageMessage(StorageMessage<V> storageMessage) {
        this.storageMessage = storageMessage;
    }
}
