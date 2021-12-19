package com.test.data.service;

import com.test.data.model.PriceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

@Slf4j
@Service (value = "PriceChannelToRsocketService")
public class PriceChannelToRsocketService {

    @Autowired
    @Qualifier(value= "rSocketRequester")
    private RSocketRequester rSocketRequester;

    public void readPrices(@Payload PriceData request) {
        log.info(" PriceChannelToRsocketService Message Recived  {} ",request );

        Object o = rSocketRequester.route("currentMarketData") .data(request.getPrice()).retrieveMono(String.class).block();

    }

}
