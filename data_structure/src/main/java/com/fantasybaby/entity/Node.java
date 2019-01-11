package com.fantasybaby.entity;

import java.io.Serializable;
/**
 * 线性链表的Node 存在两个域  数据域 和指针域
 * @author Reid.Liu
 *
 * @param <T>
 */
public class Node<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4339114890944879710L;
	private Object data;
	private Node<T> next;
	public Node() {
	}
	
	public Node(Object data, Node<T> next) {
		super();
		this.data = data;
		this.next = next;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
