package com.test.data.service;

import com.test.data.model.PriceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service (value = "PriceChannelToDbService")
public class PriceChannelToDbService {

    public void readPrices(@Payload PriceData request) {
        log.info(" PriceChannelToDbService Message Recived  {} ",request );
    }

}
