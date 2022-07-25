package com.chen2059;

import com.chen2059.business.StorageMessage;
import com.chen2059.business.StorageMessageByKafka;
import com.chen2059.common.IConnectionManger;
import com.chen2059.common.map.ConnectSessionMangerByMap;
import com.chen2059.endpoint.IEndPoint;
import com.chen2059.endpoint.factory.endpoint.TcpSEndPointFactory;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * netty服务器
 *
 * @author 陈国震
 * @date 2022-07-17
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.chen2059.**.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class NettyServer {

    public static void main(String[] args) {
//        SpringApplication.run(NettyServer.class, args);
        IConnectionManger<Channel> manger = new ConnectSessionMangerByMap<>();
        StorageMessageByKafka<String> message = new StorageMessageByKafka<>();
        IEndPoint netty = new TcpSEndPointFactory().createTcpServer("Netty", manger, message);
        try {
            netty.init().start();
            log.info("<tset>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
