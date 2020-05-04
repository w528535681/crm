package com.shsxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@MapperScan("com.shsxt.crm.dao")
@EnableScheduling
public class Starter {

    public static void main(String[] args) {

        SpringApplication.run(Starter.class);
    }
}
