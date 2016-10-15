package com.fantasybaby.hibernateValidtor;

import org.json.JSONObject;
import org.junit.Test;

import com.fantasybaby.hibernateValidtor.bean.ClassParent;
import com.fantasybaby.hibernateValidtor.bean.UserLogin;

public class FilePathTest {
	@Test
	public void Test1(Class classStr){
		/*String path = FilePathTest.class.getResource("testExcel.xlsx").getPath();
		System.out.println(path);*/
		try {
//			Object newInstance = classStr.newInstance();
//			Class superclass = classStr.getSuperclass();
//			Object newInstance = superclass.newInstance();
//			System.out.println(superclass);
			ClassParent c = new UserLogin();
			JSONObject js = new JSONObject(c);
			String string = js.toString();
			System.out.println(string);
//			UserLogin newInstance = UserLogin.class.newInstance();
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		new FilePathTest().Test1(UserLogin.class);
	}
}
