package com.fantasybaby.consumer;

import com.fantasybaby.service.IDubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"customer.xml"});
        context.start();
        IDubboService demoService = (IDubboService) context.getBean("demoService"); // obtain proxy object for remote invocation
        String hello = demoService.getName(); // execute remote invocation
        System.out.println("consumer" + hello+ "----"); // show the result
    }
}
