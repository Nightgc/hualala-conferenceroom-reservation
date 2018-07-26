package com.hualala.conference;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.hualala.conference.dao")
public class HualalaConferenceroomReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HualalaConferenceroomReservationApplication.class, args);
    }
}
