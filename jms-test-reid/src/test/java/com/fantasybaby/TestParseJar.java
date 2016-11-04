package com.fantasybaby;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;

import com.fantasybaby.client.Chat;

public class TestParseJar {
	@Test
	public void test1() {
		try {

			JarFile file = new JarFile("F:\\jms-test-reid-0.0.1-SNAPSHOT.jar");
			Enumeration<JarEntry> ee = file.entries();
			List<JarEntry> jarEntryList = new ArrayList<JarEntry>();
			while (ee.hasMoreElements()) {
				JarEntry entry = (JarEntry) ee.nextElement();
				if ( entry.getName().endsWith(".class")) {
					jarEntryList.add(entry);
				}
			}
			for (JarEntry entry : jarEntryList) {
				System.out.println(entry.getName());
				String className = entry.getName().replace('/', '.');
				className = className.substring(0, className.length() - 6);
//				try {
					System.out.println(className);
					System.out.println(entry);
					Chat chat = (Chat) Class.forName("com.fantasybaby.client.Chat").newInstance();
					
//					System.out.println(Thread.currentThread().getContextClassLoader().loadClass(className));
//					clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
			}
			file.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
