package com.fantasybabymg.test;

import java.util.List;

import com.exacttarget.fuelsdk.ETCampaign;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETEmail;
import com.exacttarget.fuelsdk.ETEmail.Type;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETList;
import com.exacttarget.fuelsdk.ETList.Classification;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSubscriber;
import com.exacttarget.fuelsdk.ETTriggeredEmail;
import com.exacttarget.fuelsdk.ETTriggeredEmail.Status;
import com.exacttarget.fuelsdk.internal.TriggeredSend;

public class TestSendMail {
	/**
	 * @param args
	 * @throws ETSdkException
	 */
	public static void main(String[] args) throws ETSdkException {
		
		ETClient client = new ETClient("/fuelsdk.properties");
		System.out.println("---------------start  list--------------");
		ETList list = client.retrieveObject(ETList.class, "key=List238956");
		System.out.println(list);
		System.out.println(list.getDescription());
		System.out.println("---------------end  list--------------");
		ETFilter filter = new ETFilter();
		/*ETExpression ex = new ETExpression();
		ex.setProperty("ListID=19713571");
		filter.setExpression(ex);*/
		/*filter.setProperty("ListID=19713571");*/
		List<ETSubscriber> retrieveObjects = client.retrieveObjects(ETSubscriber.class,"key=fantasybabymg@gmail.com");
//		ETResponse<ETSubscriber> retrieve = ETList.retrieve(client, ETSubscriber.class, null, null, filter);
		System.out.println("---------------start  Subscriber--------------");
//		List<ETSubscriber> subscribers = retrieve.getObjects();
		for (ETSubscriber etSubscriber : retrieveObjects) {
			System.out.println(etSubscriber);
		}
		System.out.println("---------------end  Subscriber--------------");
		ETTriggeredEmail email = new ETTriggeredEmail();
		ETEmail mailContent =  new ETEmail();
		mailContent.setId("1111111");
		mailContent.setClient(client);
		mailContent.setKey("SDK Example");
		mailContent.setSubject("Hello");
		mailContent.setTextBody("helloWOrld");
		mailContent.setType(Type.HTML);
		mailContent.setIsHtmlPaste(true);
		mailContent.setHtmlBody("T%3Cb%3ESome%20HTML%20Goes%20here%3C%2Fb%3E");
		email.setClient(client);
		email.setName("SDKTriggeredSend");
//		System.out.println("clientId" + client.getClientId());
		email.setAutoAddSubscribers(true);
		email.setKey("mwsfbs7tmct5jkq8crjcj96y");
		email.setEmail(mailContent);
		email.setDescription("description");
		email.setStatus(Status.NEW);
//		client.update(email);
//		email.setList(list);
		System.out.println("-------------------start send maill-------------------");
		ETResponse<ETTriggeredEmail> send = email.send(retrieveObjects);
		System.out.println(send);
		System.out.println("-------------------end  send maill-------------------");
		
	/*	ETDataExtensionRow etDataextendRow = new ETDataExtensionRow();
		etDataextendRow.setClient(client);
		etDataextendRow.setDataExtensionKey("subscribers");
		etDataextendRow.setColumn("EmailAddress", "625353861@qq.com");
		etDataextendRow.setColumn("FirstName","Reid" );*/
		
		/*ETCampaign campaign = new ETCampaign();
		campaign.setClient(client);
		campaign.setDescription("hello description");
		campaign.setName("SDKCampaign");
		ETEmail eteMail = new ETEmail();
		eteMail.setClient(client);
		eteMail.set*/
	/*	TriggeredSend send = new TriggeredSend();
		send.setClient();*/
	}

}
