package com.fantasybaby.thread.timer;

import java.util.Timer;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTaskChild(), 0, 1000);
	}
}
