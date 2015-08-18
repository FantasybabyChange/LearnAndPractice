package com.fantasybaby.inter.impl;

import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractSQLList;
/**
 * A simple implementation about liner List sequential storage
 * @author Reid.Liu
 *
 * @param <T>
 */
public class SimpleSQLList<T> extends AbstractSQLList<T> {
	private int DEFAULT_MAX_LENGTH =1000000;
	private int DEFAULT_LENGTH =10;
	public SimpleSQLList() {
		init();
	}
	public SimpleSQLList(int _length,int _maxLength) {
		try {
			init(_length,_maxLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SimpleSQLList(int _length) {
		try {
			init(_length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init(){
		currentIndex = 0;
		length = DEFAULT_LENGTH;
		maxLength = DEFAULT_MAX_LENGTH;
		data = new Object[length];
	}
	@Override
	public void init(int _length,int _maxLength) throws FantasyBabyException {
		currentIndex = 0;
		if (_length <= _maxLength) {
			length = _length;
			maxLength = _maxLength;
		}else if(_length == 0){
			length = DEFAULT_LENGTH;
		}else{
			throw new FantasyBabyException("The parameter is illegal init method");
		}
		data = new Object[length];
	}
	@Override
	public void init(int _length) throws FantasyBabyException {
		currentIndex = 0;
		if (_length > 0) {
			length = _length;
			
		}else if(_length == 0){
			length = DEFAULT_LENGTH;
		}else if(length < 0){
			throw new FantasyBabyException("The parameter is illegal init method");
		}
		maxLength = DEFAULT_MAX_LENGTH;
		data = new Object[length];
	}
	@Override
	public int getLength() {
		return length;
	}

	@Override
	public int getMaxLength() {
		return maxLength;
	}
	@Override
	public boolean setMaxLength(int _maxLength) {
		boolean isSet = false;
		if (_maxLength >= length) {
			maxLength = _maxLength;
			isSet = true;
		}
		return isSet;
	}
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index <= length) {
			return (T) data[index];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T prior(T t) {
		int locate = locate(t);
		if (locate > 0) {
			return (T) data[locate -1];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next(T t) {
		int locate = locate(t);
		if (locate < length - 1) {
			return (T) data[locate + 1];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int locate(T t) {
		int count = -1;
		boolean flag = false;
		if(data != null && data.length > 0){
			count = 0;
			for (Object childData : data) {
				T child = (T)childData;
				if(child.equals(t)){
					flag = true;
					return count; 
				}
				count ++ ;
				if (count >= currentIndex) {
					break;
				}
			}
		}
		return flag ?count : -1;
	}
	@Override
	public boolean add(T t){
		boolean isComplete =false;
		try {
			ensureCapacity();
			data[currentIndex] = t;
			currentIndex++;
			isComplete = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isComplete;
	}
	/**
	 * 事实证明 这个性能 没有下面的方法好  100000 条数据
	 */
	/*@Override
	public boolean insert(int index, T t){
		boolean isComplete = false;
		try {
			ensureCapacity();
			if (index >0 && index <= currentIndex + 1) {
				if (currentIndex + 1 == index) {
					data[currentIndex] = t;
				}else{
					Object tmpT = null;
					Object tmpT2 = null;
					for (int i = 0; i <= currentIndex; i++) {
						if (i == index -1) {
							tmpT = data[i];
							data[i] = t;
						}else if(i >= index){
							if (tmpT != null) {
								tmpT2 = data[i];
								data[i] = tmpT;
								tmpT = null;
							}else{
								tmpT = data[i];
								data[i] = tmpT2;
								tmpT2 = null;
							}
						}
					}
				}
				currentIndex++;
			}else{
				throw new FantasyBabyException("index is illegal");
			}
			isComplete = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isComplete;
	}*/
	@Override
	public boolean insert(int index, T t){
		boolean isComplete = false;
		try {
			if (index >0 && index <= currentIndex + 1) {
				if (currentIndex + 1 == index) {
					ensureCapacity();
					data[currentIndex] = t;
				}else{
					Object[] tmp = new Object[currentIndex + 1];
					for (int i = 0; i < currentIndex + 1; i++) {
						if (i < index-1) {
							tmp[i] = data[i];
						}else if(i == index-1){
							tmp[i] = t;
						}else{
							tmp[i] = data[i - 1];
						}
						
					}
					data = tmp;
				}
				currentIndex++;
			}else{
				throw new FantasyBabyException("index is illegal");
			}
			isComplete = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isComplete;
	}
	/**
	 * Use this method
	 * @throws FantasyBabyException 
	 */
	public void ensureCapacity() throws FantasyBabyException{
		if (currentIndex >= length) {
			if (length + DEFAULT_LENGTH >= maxLength) {
				throw new FantasyBabyException("out of memory");
			}
			length += DEFAULT_LENGTH;
			Object[] newObject = new Object[length];
			for (int i = 0; i < data.length; i++) {
				if (i <= currentIndex) {
					newObject[i] = data[i];
				}
			}
			data = newObject;
		}
	}
	@Override
	public boolean delete(int index) {
		boolean isComplete = false;
		for (int i = 0; i < currentIndex; i++) {
			if (i >= index) {
				data[i - 1] = data[i];
			}
		}
		currentIndex--;
		isComplete = true;
		return isComplete;
	}

	@Override
	public boolean delete(T t) {
		boolean isComplete = false;
		int locate = locate(t);
		if (locate == -1) {
			return isComplete;
		}
		for (int i = 0; i < currentIndex; i++) {
			if (i >= locate) {
				data[i - 1] = data[i];
			}
		}
		currentIndex--;
		isComplete = true;
		return isComplete;
	}

	@Override
	public boolean empty() {
		return length > 0;
	}

	@Override
	public void clear() {
		currentIndex = -1;
		length = 0;
		data = null;
	}
	@Override
	public int getCurrentIndex() {
		return currentIndex;
	}
}
