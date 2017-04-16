package com.fantasybaby.pubSub;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

public class TBrower implements MessageListener{
	private TopicConnection connection;
	private TopicSession session;
	private Topic topic;
	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage){
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("receive message:" + text);
			}
		} catch (Exception e) {
		}
		
	}
	public TBrower(){
		
	}
	public TBrower(String factoryName,String topicName){
		try {
			Context context = new InitialContext();
			TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(factoryName);
			connection = (TopicConnection) factory.createConnection("walleuser","walle123");
			//持久化订阅需要设置客户ID
			connection.setClientID("xige");
			session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			topic = (Topic) context.lookup(topicName);
			//非持久化订阅
//			TopicSubscriber subScriber = session.createSubscriber(topic);
			//持久化订阅
			TopicSubscriber subScriber = session.createDurableSubscriber(topic, "lenderTopic");
			subScriber.setMessageListener(this);
			connection.start();
			System.out.println("waiting input message");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TBrower lender = new TBrower("TopicConnectionFactory", "lenderTopic");
	}
}
