package com.fantasybaby.thread.threadlocal.login;

import com.fantasybaby.validtor.context.SystemContext;
import com.fantasybaby.validtor.hibernateValidtor.bean.UserLoginVO;
import com.fantasybaby.thread.threadlocal.ubean.Criterion;

/**
 * 通过用户登录来尝试使用ThreadLocal
 */
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
