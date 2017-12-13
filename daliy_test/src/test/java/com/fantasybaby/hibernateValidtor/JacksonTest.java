package com.fantasybaby.hibernateValidtor;

import java.util.ArrayList;
import java.util.List;

import com.fantasybaby.validtor.hibernateValidtor.bean.UserChildList;
import com.fantasybaby.validtor.hibernateValidtor.bean.UserLoginVO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	@org.junit.Test
	public void Test(){
		 ObjectMapper mapper = new ObjectMapper();  
//	      mapper.enableDefaultTyping();  
//	      mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
	  
	        try {  
	        	UserChildList<UserLoginVO> usc = new UserChildList<UserLoginVO>();
	        	List<UserLoginVO> list1 = new ArrayList<UserLoginVO>();
	        	UserLoginVO ul1 = new UserLoginVO();
	        	ul1.setUsername("fuck1");
	        	UserLoginVO ul2 = new UserLoginVO();
	        	ul2.setUsername("fuck2");
	        	list1.add(ul1);
	        	list1.add(ul2);
	        	usc.setList(list1);
	            String writeValueAsString = mapper.writeValueAsString(usc);
	            JavaType constructParametricType = mapper.getTypeFactory().constructParametricType(UserChildList.class, UserLoginVO.class);
	            UserChildList<UserLoginVO> readValue = mapper.readValue(writeValueAsString, constructParametricType);
//	            System.out.println(writeValueAsString);
	            System.out.println(readValue);
	            List<UserLoginVO> list = readValue.getList();
	            for (UserLoginVO userLogin : list) {
					System.out.println(userLogin.getUsername());
				}
	        }  catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
}
