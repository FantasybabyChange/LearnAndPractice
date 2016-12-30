package com.fantasybaby.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

import com.fantasybaby.log.LoggerUtils;

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
		LoggerUtils.init();
		_logger = LoggerFactory.getLogger(QBorrower.class);
	}
	public QBorrower(String queueFactory,String requstQueueName,String responseQueueName){
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(queueFactory);
			queueConnection = connectionFactory.createQueueConnection("walleuser","walle123");
			queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			requestQueue = (Queue) context.lookup(requstQueueName);
			responseQueue = (Queue) context.lookup(responseQueueName);
			queueConnection.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void sendLoanRequest(String firstParam,String secondParam){
		try {
			System.out.println("---------start create Message---------");
			MapMessage messageMap = queueSession.createMapMessage();
			messageMap.setString("firstParam", firstParam);
			messageMap.setString("secondParam", secondParam);
			QueueSender sender = queueSession.createSender(requestQueue);
			sender.send(messageMap);
			System.out.println("---------afterSendMessage---------");
			QueueReceiver createReceiver = queueSession.createReceiver(responseQueue);
			TextMessage receiveMessage = (TextMessage) createReceiver.receive(3000);
			String text = receiveMessage.getText();
			System.out.println(text+" -- borrower receive");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		
		/*QBorrower borrower = new QBorrower("QueueConnectionFactory","requestQueue","responseQueue");
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));*/
		_logger.info("-----start borrower");
		while (true) {
			/*String readLine = reader.readLine();
			if(readLine.equals("exit")){
				System.exit(0);
			}else{
			}*/
		}
	}
}
