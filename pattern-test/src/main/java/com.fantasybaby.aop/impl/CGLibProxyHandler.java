package com.fantasybaby.aop.impl;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cgLib来实现 aop
 */
public class CGLibProxyHandler  implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = method.getName();
        System.out.println("cglib start ===" + name);
        Object returnObject = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib end ===" + name);
        return returnObject;
    }
}
