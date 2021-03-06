package com.fantasybaby.gradle.customer;

import com.fatansybaby.gradle.service.IDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value = "consumer")
public class Consumer {
//    @Reference(version = "1.0.0")
    @Reference(version = "1.0.0")
    private IDubboService dubboService;

    public String  sayHello(){
        return dubboService.getName();
    }
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("customer.xml");
        context.start();
        Consumer consumer = (Consumer) context.getBean("consumer");
        System.out.println(consumer.sayHello());
        /*IDubboService demoService = (IDubboService) context.getBean("demoService"); // obtain proxy object for remote invocation
        String hello = demoService.getName(); // execute remote invocation
        System.out.println("consumer" + hello+ "----"); // show the result*/
    }
}
