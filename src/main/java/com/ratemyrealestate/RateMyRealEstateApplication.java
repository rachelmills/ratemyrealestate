package com.ratemyrealestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class RateMyRealEstateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateMyRealEstateApplication.class, args);
    }
}
