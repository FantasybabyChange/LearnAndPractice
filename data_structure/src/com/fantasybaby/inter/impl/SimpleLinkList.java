package com.fantasybaby.inter.impl;

import com.fantasybaby.entity.Node;
import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractLinkList;
/**
 * A simple implementation about link list
 * @author Reid.Liu
 *
 * @param <T>
 */
public  class SimpleLinkList<T> extends AbstractLinkList<T> {
	
	public SimpleLinkList() {
		init();
	}
	@Override
	public void init() {
		if (size == 0) {
			head = new Node<T>();
		}
		
	}
	/**
	 * use the index to get the data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) throws FantasyBabyException {
		if (index < 0 || index >= size) {
			throw new FantasyBabyException("index out of bound");
		}
		int count = 0;
		Node<T> next = head.getNext();
		while (count != index) {
			next = next.getNext();
			count++;
		}
		return (T) next.getData();
	}
	/**
	 * use the index to get the data
	 */
	@Override
	public Node<T> getNode(int index) throws FantasyBabyException {
		if (index < 0 || index >= size) {
			throw new FantasyBabyException("index out of bound");
		}
		int count = 0;
		Node<T> next = head.getNext();
		while (count != index) {
			next = next.getNext();
			count++;
		}
		return next;
	}

	@Override
	public T next(T t) {
		return null;
	}

	@Override
	public boolean insert(int index, Object t) throws FantasyBabyException {
		boolean isComplete = false;
		if (index < 0 || index > size) {
			throw new FantasyBabyException("index out of bound");
		}
		Node<T> nodeNew = new Node<T>();
		nodeNew.setData(t);
		if (index == 0) {
			nodeNew.setNext(head.getNext());
			head.setNext(nodeNew);
		}else{
			Node<T> nodePro = getNode(index-1);
			nodeNew.setNext(nodePro.getNext());
			nodePro.setNext(nodeNew);
		}
		size++;
		isComplete = true;
		return isComplete;
	}

	@Override
	public boolean set(int index, Object t) throws FantasyBabyException {
		boolean isComplete = false;
		if (index < 0 || index >= size) {
			throw new FantasyBabyException("index out of bound");
		}
		int count = 0;
		Node<T> next = head.getNext();
		while (count != index) {
			next = next.getNext();
			count++;
		}
		next.setData(t);
		isComplete = true;
		return isComplete;
	}
	/**
	 * insert in last index 
	 */
	@Override
	public boolean add(Object t) throws FantasyBabyException {
		boolean isComplete = false;
		Node<T> nodeTmp = new Node<T>();
		nodeTmp.setData(t);
		if (size == 0) {
			head.setNext(nodeTmp);
		}else{
			Node<T> node = getNode(size-1);
			if (node != null) {
				node.setNext(nodeTmp);
			}else{
				throw new FantasyBabyException("This node is not exist");
			}
		}
		size++;
		isComplete = true;
		return isComplete;
	}
	/**
	 * insert at pro node
	 */
	@Override
	public  boolean addPro(Object[] t) throws FantasyBabyException{
		boolean isComplete = false;
		if (t != null && t.length > 0) {
			for (int i = (t.length-1); i >= 0; i--) {
				Node<T> nodeTmp = new Node<T>();
				nodeTmp.setData(t[i]);
				nodeTmp.setNext(head.getNext());
				head.setNext(nodeTmp);
				size++;
			}
		}
		isComplete = true;
		return isComplete;
	}
	@Override
	public boolean delete(int index) throws FantasyBabyException {
		boolean isComplete = false;
		if (index < 0 || index >= size) {
			throw new FantasyBabyException("index out of bound");
		}
		if (index == 0) {
			head.setNext(head.getNext().getNext());
		}else{
			Node<T> nodePro = getNode(index-1);
			Node<T> nextNow = nodePro.getNext();
			nodePro.setNext(nextNow.getNext());
		}
		isComplete = true;
		size--;
		return isComplete;
	}

	@Override
	public boolean delete(T t) {
		return false;
	}

	@Override
	public boolean empty() {
		return size > 0;
	}

	@Override
	public void clear() {
		head.setNext(null);
		size = 0;
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public Node<T> getHeadeNode() {
		return head;
	}
	@Override
	public void setSize(int _size) {
		this.size = _size;
	}

}
