package com.fantasybaby.aop.impl;

import com.fantasybaby.aop.IDao;

public class DaoImpl implements IDao {
    @Override
    public void insert() {
        System.out.println("insert data");
    }

    @Override
    public void delete() {
        System.out.println("delete data");
    }
}
