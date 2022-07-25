package com.chen2059.endpoint.factory.endpoint;

import com.chen2059.business.StorageMessage;
import com.chen2059.common.IConnectionManger;
import com.chen2059.endpoint.IEndPoint;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-25
 */
public class UdpSEndPointFactory implements AbstractSEndPointFactory {


    @Override
    public IEndPoint createTcpServer(String type, IConnectionManger connectionManger, StorageMessage storageMessage) {
        return null;
    }

    @Override
    public IEndPoint createTcpClient() {
        return null;
    }

    @Override
    public IEndPoint createUdpServer(String type) {
        return null;
    }

    @Override
    public IEndPoint createUdpClient() {
        return null;
    }
}
