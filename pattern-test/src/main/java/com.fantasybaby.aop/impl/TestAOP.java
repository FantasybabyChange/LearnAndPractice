package com.fantasybaby.aop.impl;

import com.fantasybaby.aop.IDao;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class TestAOP {
    public static void main(String[] args) {
        /**
         * 静态代理
         */
        IDao dao = new LogHandler(new DaoImpl());
        dao.insert();
//        IDao proxyDao = (IDao) Proxy.newProxyInstance(LogProxyHandler.class.getClassLoader(), new Class<?>[]{IDao.class}, new LogProxyHandler(new DaoImpl()));
        /**
         * 动态代理
         */
        LogProxyHandler proxy = new LogProxyHandler();
        IDao proxyDao = (IDao) proxy.bind(new DaoImpl());
        proxyDao.insert();
        IDao proxyUserDao = (IDao) proxy.bind(new DaoUserImpl());
        proxyUserDao.insert();

        Enhancer enhance = new Enhancer();
        enhance.setSuperclass(DaoUserImpl.class);
        enhance.setCallback(new CGLibProxyHandler());
        IDao daoCG = (IDao) enhance.create();
        daoCG.insert();
    }
}
