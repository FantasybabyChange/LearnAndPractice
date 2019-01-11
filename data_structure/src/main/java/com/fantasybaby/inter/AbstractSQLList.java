package com.fantasybaby.inter;

import com.fantasybaby.exception.FantasyBabyException;

/**
 * Use another way to implement the liner list 
 * sequential storage structure  
 * @author FantasyBaby
 *
 */
public abstract class AbstractSQLList<T> {
	protected Object[] data = null;
	/**
	 * 表实际有数据的长度
	 */
	protected int currentIndex;
	/**
	 * 已经实例化的空间大小
	 */
	protected int length;
	/**
	 * 最大长度 
	 */
	protected int maxLength; 
	
	public abstract void init();
	/**
	 * init this liner list
	 */
	public abstract void init(int length,int maxLength) throws FantasyBabyException ;
	/**
	 * init this liner list
	 */
	public abstract void init(int length)throws FantasyBabyException;
	/**
	 * 
	 * @return the liner_list length
	 */
	public abstract int getLength();
	
	/**
	 * 
	 * @return the liner_list length
	 */
	public abstract int getMaxLength();
	/**
	 * 
	 * @return the liner_list length
	 */
	public abstract int getCurrentIndex();
	
	
	/**
	 * 
	 * @return 
	 */
	public abstract boolean setMaxLength(int _length);
	/**
	 * 
	 * @param index
	 * @return the certain data element
	 */
	public abstract T get(int index);
	/**
	 * 
	 * @param t
	 * @return get the prior element 
	 * data from current data
	 */
	public abstract T prior(T t);
	/**
	 * 
	 * @param t
	 * @return get the next element data from current data
	 */
	public abstract T next(T t);
	
	/**
	 * 
	 * @param t
	 * @return the index about current data elemnt
	 */
	public abstract int locate(T t);
	/**
	 * 
	 * @param index
	 * @param t
	 * @return if insert successful return true
	 */
	public abstract boolean insert(int index,T t);
	/**
	 *
	 * @param t
	 * @return if add successful return true
	 */
	public abstract boolean add(T t);
	/**
	 * delete the data element by index
	 * @param index
	 * @return
	 */
	public abstract boolean delete(int index);
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
	 * clear this line list
	 */
	public abstract void clear();


}
