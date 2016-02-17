package com.augmentum.lakana.newMedia.listener;


import java.util.Timer;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.augmentum.lakana.newMedia.util.TaskTimingUtil;

public class TaskManager implements ServletRequestListener {

	private Timer timer;
	public static final long NO_DELAY = 0;
	public static final long PERIOD_WEEK = 1000*10;

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		timer = new Timer("解析Feed",true);
		timer.schedule(new TaskTimingUtil(),NO_DELAY, PERIOD_WEEK);
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		//timer.cancel();
	}

}
