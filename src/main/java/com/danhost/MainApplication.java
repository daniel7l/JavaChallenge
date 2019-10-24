package com.danhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySource({"classpath:challenge.properties"})


@SpringBootApplication
//@ComponentScan({"com.danhost"})
//@EntityScan("com.danhost.entity")
@EnableJpaRepositories("com.danhost.entity")

public class MainApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(MainApplication.class, args);

    }

}