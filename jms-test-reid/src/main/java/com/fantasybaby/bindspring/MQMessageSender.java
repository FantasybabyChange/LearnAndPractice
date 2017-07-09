package com.fantasybaby.bindspring;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQMessageSender implements  IMessageSender {

    private JmsTemplate jmsTemplate;

    public MQMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    @Override
    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(message);
    }

}