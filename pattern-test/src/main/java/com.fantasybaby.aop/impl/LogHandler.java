package com.fantasybaby.aop.impl;

import com.fantasybaby.aop.IDao;

/**
 * 使用装饰模式
 */
public class LogHandler implements IDao{
    private IDao dao ;
    LogHandler(IDao dao){
        this.dao = dao;
    }
    @Override
    public void insert() {
        System.out.println("start insert");
        dao.insert();
        System.out.println("end insert");
    }

    @Override
    public void delete() {
        System.out.println("start delete");
        dao.delete();
        System.out.println("end delete");
    }
}
