package com.fantasybaby.threadTest;

import com.fantasybaby.context.SystemContext;
import com.fantasybaby.hibernateValidtor.bean.UserLoginVO;
import com.fantasybaby.ubean.Criterion;


public class UserLoginThread implements Runnable{
	public UserLoginThread() {
		System.out.println(Thread.currentThread().getName() +" 线程名字");
		
	}
	@Override
	public void run() {
		UserLoginVO user = new UserLoginVO();
		user.setUsername("user" + Thread.currentThread().getName());
		Criterion<Object> cri =  new Criterion<Object>();
		cri.setCriterion(user);
		SystemContext.setCriterionMap(cri);
		UserLoginVO.getUser();
	}
}
