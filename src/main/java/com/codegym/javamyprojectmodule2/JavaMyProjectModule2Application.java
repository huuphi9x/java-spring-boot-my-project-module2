package com.codegym.javamyprojectmodule2;

import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.service.impl.ClubServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaMyProjectModule2Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaMyProjectModule2Application.class, args);
    }

    @Bean
    public ClubService clubService(){
        return new ClubServiceImpl();
    }

}
