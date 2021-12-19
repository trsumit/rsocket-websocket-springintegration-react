package com.test.data.a;

import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketClient;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class ClientConfiguration {


    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2JsonEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2JsonDecoder()))
                .build();
    }


    @Bean (name = "rSocketRequester")
    public RSocketRequester getRSocketRequester(@Autowired  RSocketStrategies rSocketStrategies , RSocketRequester.Builder builder){

        return builder
                .rsocketConnector(rSocketConnector -> rSocketConnector.reconnect(Retry.fixedDelay(1002, Duration.ofSeconds(12))))
                .dataMimeType(MediaType.APPLICATION_JSON)
                .rsocketStrategies(rSocketStrategies)
                .setupRoute("client-handshake")
                .setupData("caller-name")
                .transport(TcpClientTransport.create("127.0.0.1", 17000));


    }



    @Bean (name = "priceChannelSubscriber")
    public RSocketRequester priceChannelSubscriber(@Autowired  RSocketStrategies rSocketStrategies , RSocketRequester.Builder builder){

        return builder
                .rsocketConnector(rSocketConnector -> rSocketConnector.reconnect(Retry.fixedDelay(1002, Duration.ofSeconds(12))))
                .dataMimeType(MediaType.APPLICATION_JSON)
                .rsocketStrategies(rSocketStrategies)
                .setupRoute("client-handshake")
                .setupData("caller-name")
                .transport(TcpClientTransport.create("127.0.0.1", 17000));


    }





}

