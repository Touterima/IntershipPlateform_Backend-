package com.microservices;

import com.microservices.entities.Application;
import com.microservices.entities.ApplicationStatus;
import com.microservices.repositories.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class ApplicationMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMsApplication.class, args);
    }}
