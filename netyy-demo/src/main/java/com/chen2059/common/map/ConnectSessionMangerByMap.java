package com.chen2059.common.map;

import com.chen2059.common.ConnectionSession;
import com.chen2059.common.IConnectionManger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-19
 */
@Service
@Slf4j
public class ConnectSessionMangerByMap<T> implements IConnectionManger<T> {

    private final Map<String, ConnectionSession<T>> sessionMap = new ConcurrentHashMap<>();


    @Override
    public ConnectionSession<T> getSession(String sessionlId) {
        return null;
    }

    @Override
    public void addSession(ConnectionSession<T> session) {

    }

    @Override
    public void addChannel(String chanelId, T channel) {

    }

    @Override
    public void addChannel(ConnectionSession<T> session, T channel) {

    }
}
