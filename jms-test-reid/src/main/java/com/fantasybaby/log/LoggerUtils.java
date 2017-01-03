package com.fantasybaby.log;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggerUtils {
	
	public static void init() {
		try {
			File file = new File("F:/workspace/javaSEWork/jms-test-reid/src/main/resources/log4j2.xml");
			String path = file.toURI().toURL().toString();
			String property = System.setProperty("log4j.configurationFile", path);
			System.out.println( property);
			LoggerContext context =(LoggerContext)LogManager.getContext(false);
			context.reconfigure();
		} catch (Exception e) {
			System.out.println("fatal error in logger utils " + e.toString());
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
