package com.fantasybaby.messageconfirm;

import com.fantasybaby.p2p.QBorrower;
import com.fantasybaby.util.UserNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * 这个类用于发送消息
 * 但是消息并非一次发送完成
 */
public class Sender {
    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private Queue requestQueue;
    private Queue responseQueue;
    private static Logger _logger ;
    static {
        _logger = LoggerFactory.getLogger(QBorrower.class);
    }
    public Sender(String queueFactory,String requstQueueName){
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(queueFactory);
            queueConnection = connectionFactory.createQueueConnection();

            queueSession = queueConnection.createQueueSession(false,Session.CLIENT_ACKNOWLEDGE);
//            requestQueue = (Queue) context.lookup(requstQueueName);
            requestQueue  =  queueSession.createQueue(requstQueueName);
            queueConnection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendGroup() throws JMSException {
        sendMessage("1","st");
        sendMessage("2","");
        sendMessage("3","");
        sendMessage("4","");
        sendMessage("5","en");
        this.queueConnection.close();
    }
    private void sendMessage(String messageStr,String flag){
        try {
            TextMessage message = queueSession.createTextMessage(messageStr);
            message.setStringProperty("marker",flag);
            QueueSender sender = queueSession.createSender(requestQueue);
            sender.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        Sender lender = new Sender("QueueConnectionFactory", "requestQueue");
        System.out.println("please input message ---------------------");
        lender.sendGroup();
        System.out.println("end message ---------------------");

    }
}
