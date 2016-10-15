package com.fantasybaby.hibernateValidtor;

import com.fantasybaby.hibernateValidtor.bean.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	@org.junit.Test
	public void Test(){
		 ObjectMapper mapper = new ObjectMapper();  
//	      mapper.enableDefaultTyping();  
//	      mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
	  
	        try {  
	            String writeValueAsString = mapper.writeValueAsString(new UserLogin());
	            System.out.println(writeValueAsString);
	        }  catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
}
