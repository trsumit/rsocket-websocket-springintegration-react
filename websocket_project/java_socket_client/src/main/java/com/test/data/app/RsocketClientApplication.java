package com.test.data.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"com.test.data"})
@ImportResource("classpath:integration.xml")

public class RsocketClientApplication {

    public static void main(String... args) {
        SpringApplication.run(RsocketClientApplication.class, args);
    }


}

