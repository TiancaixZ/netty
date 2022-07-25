package com.chen2059.common;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-21
 */
public interface IConnectionManger<T> {

    ConnectionSession<T> getSession(String sessionlId);

    void addSession(ConnectionSession<T> session);

    void addChannel(String chanelId,T channel);

    void addChannel(ConnectionSession<T> session, T channel);


}
