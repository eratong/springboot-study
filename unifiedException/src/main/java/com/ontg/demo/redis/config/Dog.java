package com.ontg.demo.redis.config;

import io.lettuce.core.event.EventBus;
import io.lettuce.core.protocol.ConnectionFacade;
import io.lettuce.core.protocol.ConnectionWatchdog;
import io.lettuce.core.protocol.ReconnectionListener;
import io.lettuce.core.resource.Delay;
import io.netty.bootstrap.Bootstrap;
import io.netty.util.Timer;
import io.netty.util.concurrent.EventExecutorGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.SocketAddress;

@Configuration
public class Dog  {

//    @Bean
//    public   ConnectionWatchdog connectionWatchdog(){
////        Delay reconnectDelay, ClientOptions clientOptions, Bootstrap bootstrap, Timer timer,
////                EventExecutorGroup reconnectWorkers, Mono< SocketAddress > socketAddressSupplier,
////                ReconnectionListener reconnectionListener, ConnectionFacade connectionFacade, EventBus eventBus
//        return new ConnectionWatchdog();
//    }
}
