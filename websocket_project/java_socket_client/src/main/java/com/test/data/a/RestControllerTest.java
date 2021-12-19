package com.test.data.a;


import com.test.data.model.PriceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestControllerTest {



    @Autowired
    @Qualifier(value ="priceChanel")
    private MessageChannel priceChanel;

    @GetMapping("/employees/{id}")
    public Object  newEmployee(@PathVariable String id) {
//
//        Object o = rSocketRequester.route("currentMarketData")
//                .data(id).retrieveMono(String.class).block();
//
//        log.info("Log {} ", o);
        PriceData priceData = new PriceData();
        priceData.setPrice(id);
        priceChanel.send(new GenericMessage(priceData));


        //return o;

        return "" + Math.random();

    }


}
