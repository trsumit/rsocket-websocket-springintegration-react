package com.test.data.controller;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import com.test.data.dto.MarketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MarketDataRepository {

    private static final int BOUND = 100;

    private Random random = new Random();




    public void add(MarketData marketData) {
        log.info("New market data: {}", marketData);
    }

    private MarketData getMarketDataResponse(String stock) {
        return new MarketData(stock, random.nextInt(BOUND));
    }
}