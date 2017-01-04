package com.fantasybaby.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * use to receive a request and reply a message to requester
 * @author FantasyBaby
 *
 */
public class QLender implements MessageListener{
	private static Logger _logger = LoggerFactory.getLogger(QLender.class);
	public static void main(String[] args) throws IOException {
		QLender lender = new QLender("QueueConnectionFactory", "requestQueue");
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
			String readLine = reader.readLine();
			if(readLine.equals("exit")){
				lender.exit();
			}
}
		
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue requestQueue;
	public QLender(String queueFc,String requestQueueStr) {
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queueFc);
			queueConnection = factory.createQueueConnection("walleuser","walle123");
			queueSession = queueConnection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
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
		_logger.info("message is coming ---------");
		if(message instanceof MapMessage){
			MapMessage mapMessage = (MapMessage) message;
			try {
				String string = mapMessage.getString("firstParam");
				_logger.info("receive a message :"+string);
				TextMessage messageBack = queueSession.createTextMessage();
				messageBack.setText("hello man");
				messageBack.setJMSCorrelationID(message.getJMSMessageID());
				Destination destination = message.getJMSReplyTo();
				QueueSender sender = queueSession.createSender((Queue) destination);
				sender.send(messageBack);
				_logger.info("finish send message");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void exit(){
		try {
			queueConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
