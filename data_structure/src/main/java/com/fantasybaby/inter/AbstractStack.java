package com.fantasybaby.inter;


/**
 * 定义一个栈的抽象类 
 * @author Reid.Liu
 *
 */
public abstract class AbstractStack<T> {
	protected Object[] data = null;
	protected int length;
	
	public abstract boolean push(T t);
	public abstract T pop();
	public abstract T top();
	public abstract void init();
	/**
	 * init this liner list
	 */
	public abstract void init(int length);
	public abstract int getLength();
	public abstract boolean empty();
}
