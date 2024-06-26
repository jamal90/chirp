package com.intuit.chirp.tweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TweetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetServiceApplication.class, args);
    }

}
