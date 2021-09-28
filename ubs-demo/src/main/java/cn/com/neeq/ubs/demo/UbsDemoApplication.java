package cn.com.neeq.ubs.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.neeq.ubs.demo.mapper")
public class UbsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UbsDemoApplication.class, args);
    }

}
