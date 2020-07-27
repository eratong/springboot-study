package com.ontg.redis.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ConnectionBuilder;
import io.lettuce.core.RedisChannelInitializer;
import io.lettuce.core.cluster.ClusterClientOptions;
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
import reactor.core.publisher.Mono;

import java.net.SocketAddress;
import java.time.Duration;

@Configuration
public class Dog  {

//    @Bean
//    public Delay reconnectDelay(){
//        return new Delay() {
//            @Override
//            public Duration createDelay(long attempt) {
//                return null;
//            }
//        };
//    }
////    @Bean
////    public ClientOptions clientOptions(){
////        return ClientOptions.builder();
////        return new ClusterClientOptions(ClientOptions.class);;
////    }
//    @Bean
//    public Bootstrap bootstrap(){return }
//    @Bean
//    public Delay reconnectDelay(){}
//    @Bean
//    public Delay reconnectDelay(){}
//    @Bean
//    public Delay reconnectDelay(){}
//    @Bean
//    public Delay reconnectDelay(){}
//    @Bean
//    public Delay reconnectDelay(){}

//    @Bean
//    public   ConnectionWatchdog connectionWatchdog(Delay reconnectDelay, ClientOptions clientOptions, Bootstrap bootstrap, Timer timer,
//                                                   EventExecutorGroup reconnectWorkers, Mono< SocketAddress > socketAddressSupplier,
//                                                   ReconnectionListener reconnectionListener, ConnectionFacade connectionFacade, EventBus eventBus){
//        return new ConnectionWatchdog(reconnectDelay,clientOptions,bootstrap,timer,reconnectWorkers,socketAddressSupplier,reconnectionListener,connectionFacade,eventBus);
//    }
    @Bean
    public RedisChannelInitializer redisChannelInitializer(){
        System.out.println("redisChannelInitializer");
        return new ConnectionBuilder().build();
    }
}
