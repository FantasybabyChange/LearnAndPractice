package com.augmentum.lakana.newMedia.util;

import java.util.TimerTask;

public class TaskTimingUtil extends TimerTask{
	@Override
	public void run() {
		System.out.println("定时任务10后开启");
		try {
			XMLParseUtil xmlParseUtil = new XMLParseUtil();
			xmlParseUtil.parseXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new XMLParseUtil());
        try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        pool.execute(new XMLParseUtil());
        pool.shutdown();*/
	}
}