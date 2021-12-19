package com.test.data.a;

import com.test.data.model.MarketData;
import com.test.data.model.MarketDataRequest;
import io.netty.buffer.ByteBuf;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketClient;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@Slf4j
public class ReconTool implements CommandLineRunner {



    @Autowired
    private ClientConfiguration clientConfiguration;

    @Autowired
    @Qualifier(value= "rSocketRequester")
    private RSocketRequester rSocketRequester;


    @Override
    public void run(String... args) throws Exception {

//
//        RSocketRequester deviceRSocketRequester = RSocketRequester.builder()
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
//                .rsocketConnector(connector -> {
//                    connector.reconnect(Retry.fixedDelay(100, Duration.ofSeconds(5L)));
//
//                })
//                .transport(TcpClientTransport.create("127.0.0.1", 17000));


     //   Object o = rSocketRequester.route("currentMarketData")
//                        .data("request-response").retrieveMono(String.class).block();
//
//        log.info("Log {} ", o);
//
//
//        System.out.println("test");

    }




}