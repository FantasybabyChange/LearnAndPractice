package com.fantasybaby.aop.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cgLib来实现 aop
 */
public class CGLibProxyHandler  implements MethodInterceptor{
    private Object obj;
    public Object bind(Object obj){
        this.obj = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = method.getName();
        System.out.println("cglib start ===" + name);
        Object returnObject = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib end ===" + name);
        return returnObject;
    }
}
