package com.fantasybaby.bindspring;

import org.apache.axis2.clustering.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fanta on 2017-07-09.
 */
public class TestMessage {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        IMessageSender sender = ctx.getBean(IMessageSender.class);
        sender.sendMessage("Hello");
        ctx.close();
    }
}
