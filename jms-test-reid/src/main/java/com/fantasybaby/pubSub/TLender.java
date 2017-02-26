package com.fantasybaby.pubSub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * 消息发布者
 * @author FantasyBaby
 *
 */
public class TLender {
	private TopicConnection connection;
	private TopicSession session;
	private Topic topic;
	public TLender(){
		
	}
	public TLender(String factoryName,String topicName){
		try {
			Context context = new InitialContext();
			TopicConnectionFactory  factory = (TopicConnectionFactory) context.lookup(factoryName);
			connection = (TopicConnection) factory.createConnection("walleuser","walle123");
			session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			topic = (Topic) context.lookup(topicName);
			connection.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void sendMessage(String messageStr){
		try {
			TextMessage message = session.createTextMessage(messageStr);
			TopicPublisher publisher = session.createPublisher(topic);
			publisher.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		TLender lender = new TLender("TopicConnectionFactory", "lenderTopic");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please input message ---------------------");
		while (true) {
			String inputStr = reader.readLine();
			if(inputStr.equals("exit")){
				System.exit(1);
			}else{
				lender.sendMessage(inputStr);
			}
			System.out.println("----------------------------");
			
		}
		
	}
}
