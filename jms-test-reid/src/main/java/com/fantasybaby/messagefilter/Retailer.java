package com.fantasybaby.messagefilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 销售商 发送消息 
 * 之前先过滤 
 * @author FantasyBaby
 *
 */
public class Retailer {
	private static Logger _logger = LoggerFactory.getLogger(Retailer.class);
	private QueueConnection connection ;
	private QueueSession session;
	private Queue requestQueue;
	public Retailer(String factoryName,String queueName){
		InitialContext context;
		try {
			context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(factoryName);
			connection = (QueueConnection) factory.createConnection("walleuser", "walle123");
			session = (QueueSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			requestQueue = (Queue) context.lookup(queueName);
			connection.start();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public void exit(){
		try {
			this.session.close();
			this.connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	public void purchase(String desc,String custType){
		try {
			_logger.info("start purchase ======");
			QueueSender sender = session.createSender(requestQueue);
			TextMessage message = session.createTextMessage();
			message.setText(desc);
			message.setStringProperty("type", custType);
			sender.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public Retailer() {
	}
	public static void main(String[] args) throws IOException {
		Retailer retailer = new Retailer("TopicConnectionFactory","requestQueue");
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
		_logger.info("-----start Retailer");
		while (true) {
			String readLine = reader.readLine();
			if(readLine.equals("exit")){
				retailer.exit();
			}else{
				String[] split = readLine.split(",");
				retailer.purchase(split[0],split[1]);
			}
		}
	}
}
