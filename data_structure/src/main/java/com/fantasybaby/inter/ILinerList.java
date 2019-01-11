package com.fantasybaby.inter;

public interface ILinerList<T> {
	/**
	 * init this liner list
	 */
	public void init(T[] tList);
	/**
	 * 
	 * @return the liner_list length
	 */
	public int getLength(T[] tList);
	/**
	 * 
	 * @param index
	 * @return the certain data element
	 */
	public T get(T[] tList,int index);
	/**
	 * 
	 * @param t
	 * @return get the prior element 
	 * data from current data
	 */
	public T prior(T[] tList,T t);
	/**
	 * 
	 * @param t
	 * @return get the next element data from current data
	 */
	public T next(T[] tList,T t);
	
	/**
	 * 
	 * @param t
	 * @return the index about current data elemnt
	 */
	public int locate(T[] tList,T t);
	/**
	 * 
	 * @param index
	 * @param t
	 * @return if insert successful return true
	 */
	public T[] insert(T[] tList,int index,T t);
	/**
	 * delete the data element by index
	 * @param index
	 * @return
	 */
	public T[] delete(T[] tList,int index);
	/**
	 * delete the data element
	 * @param t
	 * @return
	 */
	public boolean delete(T[] tList,T t);
	/**
	 * 
	 * @return if the line list is empty return true
	 */
	public boolean empty(T[] tList);
	/**
	 * clear this line list
	 */
	public void clear(T[] tList);
}
