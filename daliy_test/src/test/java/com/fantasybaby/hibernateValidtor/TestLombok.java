package com.fantasybaby.hibernateValidtor;

import com.fantasybaby.normaltest.lombok.UserBean;
import org.junit.Test;

/**
 * Created by fanta on 2017-06-05.
 */
public class TestLombok {
    @Test
    public void testLombokMethod(){
        UserBean bean = new UserBean();
        bean.setUserName("呵呵");
        bean.setAge(13);

        System.out.println(bean.getAge()+":"+bean.getUserName());


    }
}
