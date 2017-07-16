package com.fantasybaby.messageconfirm;

import com.fantasybaby.p2p.QLender;
import com.fantasybaby.util.UserNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receive implements MessageListener{
	private static Logger _logger = LoggerFactory.getLogger(Receive.class);
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue requestQueue;
	public Receive(String queueFc,String requestQueueStr) {
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queueFc);
			queueConnection = factory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false,QueueSession.CLIENT_ACKNOWLEDGE);
//			requestQueue = (Queue) context.lookup(requestQueueStr);
			requestQueue = queueSession.createQueue(requestQueueStr);
			queueConnection.start();

			QueueReceiver createReceiver = queueSession.createReceiver(requestQueue);
			_logger.info("create receiver");
			createReceiver.setMessageListener(this);
			_logger.info("----- start waiting message ------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage){
				TextMessage textMessage = (TextMessage) message;
				if(textMessage.propertyExists("marker")){
					System.out.println("messageRedelive:"+message.getJMSRedelivered());
					boolean jmsRedelivered = message.getJMSRedelivered();
					String text = textMessage.getText();
					System.out.println("receive message:" + text);
					if(text.equals("3")){
						throw new Exception("1");
					}
					String marker = textMessage.getStringProperty("marker");
					if(marker.equals("en")){
						System.out.println("end receive message");
						//执行后 重启服务 将不会再次收到消息
						textMessage.acknowledge();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		Receive lender = new Receive("QueueConnectionFactory", "requestQueue");
	}
}
