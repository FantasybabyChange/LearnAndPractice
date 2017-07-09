package com.fantasybaby.messagetransaction;

import com.fantasybaby.p2p.QBorrower;
import com.fantasybaby.util.UserNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * 发送多条消息
 * 事务提交后 消息服务才会统一提交
 */
public class Sender {
    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private Queue requestQueue;
    private static Logger _logger ;
    static {
        _logger = LoggerFactory.getLogger(QBorrower.class);
    }
    public Sender(String queueFactory, String requstQueueName){
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(queueFactory);
            queueConnection = connectionFactory.createQueueConnection(UserNameUtil.USERNAME,UserNameUtil.USERPWD);

            queueSession = queueConnection.createQueueSession(true,Session.CLIENT_ACKNOWLEDGE);
            requestQueue = (Queue) context.lookup(requstQueueName);
            queueConnection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendGroup() throws JMSException {
        sendMessage("1","st");
        sendMessage("2","");
//        sendMessage("3","");
//        sendMessage("4","");
        sendMessage("5","en");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            queueSession.rollback();
        }
        //提交事务
        queueSession.commit();
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
