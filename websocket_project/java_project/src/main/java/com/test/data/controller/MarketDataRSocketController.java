package com.test.data.controller;

import com.test.data.dto.MarketData;
import com.test.data.dto.MarketDataRequest;
import com.test.data.dto.Message;
import com.test.data.dto.TextMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class MarketDataRSocketController {

    private final MarketDataRepository marketDataRepository;

    @Autowired
    SimpMessagingTemplate template;

    public MarketDataRSocketController(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    @MessageMapping("currentMarketData")
    public Mono<String> currentMarketData(String marketDataRequest) {

        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setMessage(marketDataRequest);
        template.convertAndSend("/topic/message", textMessageDTO);

        log.info("Message recived  {} ", marketDataRequest);
        return  Mono.just("test" + Math.random());

    }

    @MessageExceptionHandler
    public Mono<MarketData> handleException(Exception e) {
        log.error ("Exception e {} ", e);
        return Mono.just(MarketData.fromException(e));
    }

    @MessageMapping("request-response")
    public Message requestResponse(Message request) {
        log.info("Received request-response request: {}", request);
        // create a single Message and return it
        return new Message("SERVER", "RESPONSE");
    }

    @MessageMapping("fire-request-response")
    public Mono<Void> fireForgetResponse(Message request) {
        log.info("Received request-response request: {}", request);
        // create a single Message and return it
        return Mono.empty();
    }


    @ConnectMapping
    public Mono<Void> connect(RSocketRequester requester) {
        requester.rsocket()
        .onClose()
                .doFirst ( () -> {
                    log.info("s  do first {} " );
                })
                .doOnError( t->{
                    log.info("s  do error {} " , t);
                })
                .doFinally(s-> {
                    log.info("s  do finally{} " , s);
                })
                .subscribe();
        log.info("Client Connected! {} ", requester.rsocket().availability());
        return Mono.empty();
    }


    private void doFirst(RSocketRequester requester){
        log.info("Do first  {} " , requester);
    }



    private void onError(RSocketRequester requester){
        log.info("Do first  {} " , requester);
    }
}