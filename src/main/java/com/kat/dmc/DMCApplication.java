package com.kat.dmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DMCApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DMCApplication.class, args);
    }

}