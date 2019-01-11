package com.fantasybaby.inter.impl;

import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractStack;

public class SimpleSQLStack<T> extends AbstractStack<T>{
	private static int DEFAULT_INIT_LENGTH = 10;
	public  SimpleSQLStack() {
		init();
	}
	public  SimpleSQLStack(int length) {
		init(length);
	}
	@Override
	public boolean push(T t) {
		boolean isPass = false;
		ensureCapacity();
		data[length++] = t;
		isPass = true;
		return isPass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if (empty()) {
			return null;
		}
		return (T) data[--length];
	}

	@Override
	public void init() {
		data = new Object[DEFAULT_INIT_LENGTH];
	}
	@Override
	public void init(int length) {
		data = new Object[length];
		
	}
	/**
	 * Use this method
	 * @throws FantasyBabyException 
	 */
	public void ensureCapacity(){
		if (length >= data.length) {
			Object[] newObject = new Object[data.length+DEFAULT_INIT_LENGTH];
			for (int i = 0; i < data.length; i++) {
				if (i <= length) {
					newObject[i] = data[i];
				}
			}
			data = newObject;
		}
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public boolean empty() {
		return length == 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public T top() {
		if (empty()) {
			return null;
		}
		return (T) data[length-1];
	}
	
}
