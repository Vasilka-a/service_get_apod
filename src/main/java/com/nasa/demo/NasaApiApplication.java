package com.nasa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NasaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApiApplication.class, args);
    }

}
