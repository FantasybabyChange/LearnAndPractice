package com.fantasybaby.bindspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fanta on 2017-07-09.
 */
public interface IMessageSender {
    void sendMessage(String message);
    void sendMessage(String destination,String message);
}
