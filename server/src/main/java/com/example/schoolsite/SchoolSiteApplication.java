package com.example.schoolsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SchoolSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolSiteApplication.class, args);
    }

}
