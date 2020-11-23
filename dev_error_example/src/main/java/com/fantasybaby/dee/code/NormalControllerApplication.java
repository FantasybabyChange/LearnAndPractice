package com.fantasybaby.dee.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author fantasybaby
 * //
 */

@SpringBootApplication
//@EnableEurekaClient
//@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fantasybaby.dee"})
public class NormalControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NormalControllerApplication.class, args);
    }

}
