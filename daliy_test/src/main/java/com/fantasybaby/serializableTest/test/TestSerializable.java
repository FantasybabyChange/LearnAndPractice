package com.fantasybaby.serializableTest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.fantasybaby.serializableTest.vo1.UserBean;

public class TestSerializable {
	private OutputStream output = null;	
	private InputStream input = null;
	/**
	 * 如果两个相同结构的bean使用不同的序列ID
	 * 解析和反解析
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void test1() throws IOException, ClassNotFoundException{
		output = new FileOutputStream(new File("Object1.txt"));
		ObjectOutputStream objectOutPut = new ObjectOutputStream(output);
		UserBean userBean1 = new UserBean();
		userBean1.setAge(12);
		userBean1.setName("第一人");
		objectOutPut.writeObject(userBean1);
		objectOutPut.flush();
		objectOutPut.close();
		output.close();
	}
	/**如果两个相同结构的bean使用不同的序列ID
	 * 解析和反解析
	 * 读取
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	public void test2() {
		try {
			input = new FileInputStream(new File("Object1.txt"));
			ObjectInputStream objectInput = new ObjectInputStream(input);
			Object readObject = objectInput.readObject();
			com.fantasybaby.serializableTest.vo2.UserBean userBean = (com.fantasybaby.serializableTest.vo2.UserBean) readObject; 
			System.out.println(userBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
