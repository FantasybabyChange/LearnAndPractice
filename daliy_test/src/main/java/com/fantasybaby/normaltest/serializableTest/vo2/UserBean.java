package com.fantasybaby.normaltest.serializableTest.vo2;

import java.io.Serializable;

/**
 * Created by FantasyBaby on 2016-08-30.
 */
public class UserBean implements Serializable{

    /**
	 * 
	 */
//	private static final long serialVersionUID = 4703192591080303275L;
	private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "UserBean [age=" + age + ", name=" + name + "]";
	}
    
}
