package com.fantasybaby.aop.impl;

import com.fantasybaby.aop.IDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用代理模式  来实现aop
 */
public class LogProxyHandler implements InvocationHandler{
    private Object obj;
    /*LogProxyHandler(Object obj){
        this.obj = obj;
    }*/
    LogProxyHandler(){
//        this.obj = obj;
    }
    public Object bind(Object obj){
        this.obj = obj;
     return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+" start====");
        method.invoke(obj,args);
        System.out.println(method.getName()+"   end====");
        return null;
    }
}
