package com.fantasybaby.inter.impl;

import com.fantasybaby.inter.ILinerList;

public class SimpleArrayImpl implements ILinerList<Integer> {
	@Override
	public void init(Integer[] tList) {
		tList = new Integer[4];
	}

	@Override
	public int getLength(Integer[] tList) {
		if(tList != null){
			return tList.length;	
		}
		return 0;
	}

	@Override
	public Integer get(Integer[] tList, int index) {
		if(tList != null &&index < tList.length && index >=0){
			return tList[index];
		}
		return null;
		
	}

	@Override
	public Integer prior(Integer[] tList, Integer t) {
		int locateIndex = locate(tList, t);
		if(locateIndex >= 1 ){
			return tList[locateIndex -1 ];
		}
		return null;
	}

	@Override
	public Integer next(Integer[] tList, Integer t) {
		int locateIndex = locate(tList, t);
		if(locateIndex < (getLength(tList) - 1) ){
			return tList[locateIndex + 1 ];
		}
		return null;
	}

	@Override
	public int locate(Integer[] tList, Integer t) {
		int count = -1;
		boolean flag = false;
		if(tList != null && tList.length > 0){
			count = 0;
			for (Integer integer : tList) {
				if(integer.intValue() == t.intValue()){
					flag = true;
					return count; 
				}
				count ++ ;
			}
		}
		return flag ?count : -1;
	}

	@Override
	public Integer[] insert(Integer[] tList, int index, Integer t) {
		Integer[] tmpArray = null;
		if(tList != null){
			tmpArray = new Integer[tList.length + 1];
		}else{
			tmpArray = new Integer[1];
		}
		
		for (int i = 0; i < tmpArray.length; i++) {
			if(i < index - 1){
				tmpArray[i] = tList[i];
			}else if(i == index - 1){
				tmpArray[i] = t;
			}else{
				tmpArray[i] = tList[i - 1];
			}
		}
		return tmpArray;
	}

	@Override
	public Integer[] delete(Integer[] tList, int index) {
		Integer[] tmpArray = new Integer[tList.length - 1];
		for (int i = 0; i < tmpArray.length; i++) {
			if(i < index ){
				tmpArray[i] = tList[i];
			}else{
				tmpArray[i] = tList[i + 1];
			}
		}
		return tmpArray;
	}

	@Override
	public boolean delete(Integer[] tList, Integer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean empty(Integer[] tList) {
		return tList.length > 0 ;
	}

	@Override
	public void clear(Integer[] tList) {
		tList = new Integer[tList.length];
	}

}
