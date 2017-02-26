package com.fantasybaby.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步请求/应答
 * use to send a request 
 * 
 * will use a queue   requestChannel
 * @author FantasyBaby
 *
 */
public class QBorrower {
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue requestQueue;
	private Queue responseQueue;
	private static Logger _logger ;
	static {
		//LoggerUtils.init();
		_logger = LoggerFactory.getLogger(QBorrower.class);
	}
	public QBorrower(String queueFactory,String requstQueueName,String responseQueueName){
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(queueFactory);
			queueConnection = connectionFactory.createQueueConnection("walleuser","walle123");
			
			showMetaData();
			queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			requestQueue = (Queue) context.lookup(requstQueueName);
			responseQueue = (Queue) context.lookup(responseQueueName);
			queueConnection.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void showMetaData(){
		try {
			ConnectionMetaData metaData = queueConnection.getMetaData();
			_logger.info("providerName :"+metaData.getJMSProviderName());
			_logger.info("version :"+metaData.getJMSVersion());
			Enumeration propertyNames = metaData.getJMSXPropertyNames();
			while (propertyNames.hasMoreElements()) {
				_logger.info((String)propertyNames.nextElement());
				
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void sendLoanRequest(String firstParam) throws InterruptedException{
		try {
			_logger.info("---------start create Message---------");
			MapMessage messageMap = queueSession.createMapMessage();
			messageMap.setString("firstParam", firstParam);
//			messageMap.setString("secondParam", secondParam);
			messageMap.setJMSReplyTo(responseQueue);
			QueueSender sender = queueSession.createSender(requestQueue);
			sender.send(messageMap);
			_logger.info("---------afterSendMessage---------");
//			if(createReceiver == null){
			String filter="JMSCorrelationID='" + messageMap.getJMSMessageID()+"'";
			QueueReceiver createReceiver = queueSession.createReceiver(responseQueue,filter);
//			}
			TextMessage receiveMessage = (TextMessage) createReceiver.receive();
			if(receiveMessage != null){
				String text = receiveMessage.getText();
				_logger.info(text+" -- borrower receive");
			}else{
				_logger.info(" -- no reback message");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void exit(){
		try {
			this.queueConnection.close();
			System.exit(0);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		
		QBorrower borrower = new QBorrower("QueueConnectionFactory","requestQueue","responseQueue");
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
		_logger.info("-----start borrower");
		while (true) {
			String readLine = reader.readLine();
			if(readLine.equals("exit")){
				borrower.exit();
			}else{
				borrower.sendLoanRequest(readLine);
			}
		}
	}
}
