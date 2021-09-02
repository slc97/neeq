package com.neeq.tradingDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neeq.tradingDemo.dao")
public class TradingDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradingDemoApplication.class, args);
    }

}
