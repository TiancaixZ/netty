package com.chen2059.endpoint;

/**
 *
 *
 * @author 陈国震
 * @date 2022-07-20
 */
public interface IEndPoint {

    IEndPoint init();

    void start() throws Exception ;

    void stop() throws Exception;

    void destory() throws Exception;
}
