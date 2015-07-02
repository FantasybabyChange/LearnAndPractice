package com.fantasybaby.classloader;

import java.io.UnsupportedEncodingException;

public class ConvertCode {
	public static void main(String[] args) throws UnsupportedEncodingException {  // TODO Auto-generated method stub
		String str = "/u59ec/u7c73/u5a1c/u6211/u7231/u4f60";
		String st = changeToBinaryCode(str);
		System.out.println(st);
}
	public static String changeToBinaryCode(String str) throws UnsupportedEncodingException{
		
		StringBuffer sb = new StringBuffer();
		  char[] chars = str.toCharArray();
		  for(int i = 0;i < chars.length;i++)
		  {
			  sb.append( "/u" + Integer.toHexString((int) chars[i]));
		  }
		return sb.toString();
	}
	public static String changeBinaryStrToString(String str){
		  String[] unicode = str.split("\\\\u"); 
		  StringBuffer sb = new StringBuffer();
		  for(int i = 1;i < unicode.length;i++)
			  sb.append((char) Integer.parseInt(unicode[i],16));
		return sb.toString();
	}
}
