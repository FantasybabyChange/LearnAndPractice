package com.fantasybaby.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Test Stream Word
 * 
 * @author 曦
 *
 */
public class CreateWordCode {
	private String path ;
	private static File file;
	private static InputStream input ;
	private static OutputStream output;
	
	public CreateWordCode(){
	}
	public CreateWordCode(String path){
		this.path = path;
		if (file == null) {
			file = new File(this.path);
		}
	}
	public  void writeWord(String word) throws IOException{
//		 input = new FileInputStream(file);
//		 output = new FileOutputStream(file);
		file.delete();
//		 byte[] bytes = word.getBytes();
//		 output.write(bytes, 0, bytes.length);
	}
	public  void close(){
		try {
			if (input != null) {
				output.flush();
				input.close();
			}
			if (output != null) {
				output.flush();
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
