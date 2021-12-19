package com.test.data.service;

import com.test.data.model.PriceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service (value = "PriceChannelSubscriberService")
public class PriceChannelSubscriberService {

    public void readPrices(@Payload PriceData request) {
        log.info("Message Recived  {} ",request );
    }

}
