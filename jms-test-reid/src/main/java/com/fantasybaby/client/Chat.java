package com.fantasybaby.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * http://activemq.2283324.n4.nabble.com/Exception-occur-when-JNDI-lookup-td2356071.html jndi tmp
 * @author FantasyBaby
 *
 */
public class Chat implements MessageListener{
	private TopicSession topicSession;
	private TopicPublisher publisher;
	private TopicConnection connection;
	private String userName;
	Chat(String topicFactory,String topicName,String userName) throws Exception{
		InitialContext initialContext = new InitialContext();
		TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) initialContext.lookup(topicFactory);
		TopicConnection topicConnection = topicConnectionFactory.createTopicConnection("walleuser","walle123");
		TopicSession session1 = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		TopicSession session2 = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic chatTopic = (Topic) initialContext.lookup(topicName);
		
		TopicPublisher topicPublisher = session1.createPublisher(chatTopic);
		TopicSubscriber topicSubscriber = session2.createSubscriber(chatTopic,null,true);
		topicSubscriber.setMessageListener(this);
		
		this.connection = topicConnection;
		this.topicSession = session1;
		this.publisher = topicPublisher;
		this.connection.start();
		this.userName = userName;
	}
	@Override
	public void onMessage(Message arg0) {
		TextMessage message = (TextMessage) arg0;
		try {
			System.out.println(message.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	protected void writeMessage(String text) throws JMSException{
		TextMessage message = this.topicSession.createTextMessage(this.userName+":"+text);
		this.publisher.publish(message);
	}
	public void close() throws JMSException{
		if(this.connection != null){
			this.connection.close();
		}
	}
	public static void main(String[] args) throws NamingException {
		String topicName = "mytopic";
		System.out.println("请随便输入用户名称:  ");
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
		try {
			String readLine = reader.readLine();
			String userName = readLine;
			if("".equals(readLine) || readLine == null){
				System.out.println("请随便输入用户名称 ");
			}else{
				Chat chat = new Chat("TopicConnectionFactory", topicName, userName);
				System.out.println(userName+" 开始发送消息吧 输入exit退出");
				while (true) {
					readLine = reader.readLine();
					if(readLine.equals("exit")){
						System.exit(0);
					}else{
						chat.writeMessage(readLine);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
