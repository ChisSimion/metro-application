package com.ecommerce.metro.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ecommerce.metro")
@EntityScan(basePackages = "com.ecommerce.metro.entity")
@EnableJpaRepositories(basePackages = "com.ecommerce.metro.repository")
public class SpringBootApplicationStarter
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
