package com.fantasybaby.bindspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class MQMessageReceive implements  IMessageReceive {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void receiveMessage() {
        Object o = jmsTemplate.receiveAndConvert();
        String str = (String) o;
        System.out.println("==" + str + "====");
    }
}