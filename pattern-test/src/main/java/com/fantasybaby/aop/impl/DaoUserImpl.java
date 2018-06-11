package com.fantasybaby.aop.impl;

import com.fantasybaby.aop.IDao;

public class DaoUserImpl implements IDao {
    @Override
    public void insert() {
        System.out.println("insert user data");
    }

    @Override
    public void delete() {
        System.out.println("delete user data");
    }
}
