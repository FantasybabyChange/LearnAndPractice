package com.fantasybaby.bindspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by FantasyBaby on 2017-07-16.
 * This cass will receive message by synchronized way
 */
@Component
public class MessageReceiveSyn2 implements MessageListener{
    private Logger logger = LoggerFactory.getLogger(MessageReceiveSyn2.class);

    @PostConstruct
    public void init(){
    }
    @Override
    public void onMessage(Message message) {
        logger.info("start onMessage");
        try {
            if(message instanceof TextMessage){
                    String text = ((TextMessage) message).getText();
                    logger.info(text);
            }
            logger.info("end onMessage");
        } catch (JMSException e) {
            e.printStackTrace();
        }    }
}
