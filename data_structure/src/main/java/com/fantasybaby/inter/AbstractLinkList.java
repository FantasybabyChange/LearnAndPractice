package com.fantasybaby.inter;

import com.fantasybaby.entity.Node;
import com.fantasybaby.exception.FantasyBabyException;
/**
 * A simple single list
 * @author FantasyBaby
 *
 * @param <T>
 */
public abstract class AbstractLinkList<T> {
	protected Node<T> head = null;
	protected int size = 0;
	
	/**
	 * 表实际有数据的长度
	 */
	public abstract void init();
	
	public abstract int getSize();
	public abstract void setSize(int size);
	/**
	 * init this liner list
	 */
	public abstract Node<T> getNode(int index) throws FantasyBabyException;
	/**
	 * 
	 * @param index
	 * @return the certain data element
	 */
	public abstract T get(int index) throws FantasyBabyException;
	/**
	 * 
	 * @param t
	 * @return get the next element data from current data
	 */
	public abstract T next(T t);
	
	/**
	 * 
	 * @param index
	 * @param t
	 * @return if insert successful return true
	 */
	public abstract boolean insert(int index,Object t)throws FantasyBabyException;
	/**
	 * 
	 * @param index
	 * @param t
	 * @return if set successful return true
	 */
	public abstract boolean set(int index,Object t)throws FantasyBabyException;
	/**
	 *insert in last
	 * @param t
	 * @return if add successful return true
	 */
	public abstract boolean add(Object t) throws FantasyBabyException;
	/**
	 * insert in pro 
	 * @param t
	 * @return if add successful return true
	 */
	public abstract boolean addPro(Object[] t) throws FantasyBabyException;
	/**
	 * delete the data element by index
	 * @param index
	 * @return
	 */
	public abstract boolean delete(int index)throws FantasyBabyException ;
	/**
	 * delete the data element
	 * @param t
	 * @return
	 */
	public abstract boolean delete(T t);
	/**
	 * 
	 * @return if the line list is empty return true
	 */
	public abstract boolean empty();
	/**
	 * get the head
	 * @return
	 */
	public abstract Node<T> getHeadeNode();
	/**
	 * clear this line list
	 */
	public abstract void clear();

}
