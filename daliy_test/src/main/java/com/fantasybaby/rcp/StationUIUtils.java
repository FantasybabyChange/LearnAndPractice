package com.fantasybaby.rcp;


public class StationUIUtils {
	/** 屏幕缩放比例 */
	public static double SCREEN_SCALE = 1.0;
	public static int scale(int num) {
		return (int) (num * SCREEN_SCALE);
	}
	
}
