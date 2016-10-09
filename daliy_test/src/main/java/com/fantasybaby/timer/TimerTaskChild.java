package com.fantasybaby.timer;

import java.util.Date;
import java.util.TimerTask;

public class TimerTaskChild extends TimerTask {

	@Override
	public void run() {
	  try {
		  String name = Thread.currentThread().getName();
		  System.out.println("start timer" + name);
		  System.out.println(new Date().toString());
		  for(int i = 0;i < 5; i++){
			  Thread.sleep(1000);
		  }
		  System.out.println("end timer" + name);
		  System.out.println(new Date().toString());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  

	}

}
