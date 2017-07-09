package com.fantasybaby.messagetransaction;

import com.fantasybaby.util.UserNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import java.io.IOException;

public class Receive implements MessageListener{
	private static Logger _logger = LoggerFactory.getLogger(Receive.class);
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue requestQueue;
	public Receive(String queueFc, String requestQueueStr) {
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queueFc);
			queueConnection = factory.createQueueConnection(UserNameUtil.USERNAME,UserNameUtil.USERPWD);
			queueSession = queueConnection.createQueueSession(true,QueueSession.AUTO_ACKNOWLEDGE);
			requestQueue = (Queue) context.lookup(requestQueueStr);
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
				String text = textMessage.getText();
				System.out.println(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		Receive lender = new Receive("QueueConnectionFactory", "requestQueue");
	}
}
