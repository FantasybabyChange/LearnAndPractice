package com.fantasybaby.bindspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

@Component
public class MQMessageSender implements  IMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static final String customDestination = "requestQueue1";

    @Override
    public void sendMessage(String message) {

        jmsTemplate.convertAndSend(customDestination,message);
    }

}