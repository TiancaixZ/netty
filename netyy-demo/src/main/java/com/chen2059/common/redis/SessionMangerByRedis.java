package com.chen2059.common.redis;

import com.chen2059.common.ConnectionSession;
import com.chen2059.common.IConnectionManger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 陈国震
 * @date 2022-07-19
 */
@Service
@Slf4j
public class SessionMangerByRedis implements IConnectionManger<ConnectionSession> {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public ConnectionSession getSession(String sessionId) {
        return null;
    }

    @Override
    public void addSession(ConnectionSession session) {

    }

    @Override
    public void addChannel(String sessionId, ConnectionSession channel) {

    }

    @Override
    public void addChannel(ConnectionSession session, ConnectionSession channel) {

    }
}
