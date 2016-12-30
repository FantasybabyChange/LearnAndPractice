package com.fantasybaby.classloader;

public class ClassloaderSequence {
//	private  ClassloaderSequence cl = new ClassloaderSequence();  //The result will be 1,0
	public static  int count;
	public static int count1 = 0;
//	private static ClassloaderSequence cl = new ClassloaderSequence();//The result will be 1,1,
	public  ClassloaderSequence(){
			count++;
			count1++;
		
	}
	
	/*public  ClassloaderSequence getInstance(){
		return cl;
	}*/
}