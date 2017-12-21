package com.fantasybaby.normaltest.java8test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fantasybaby.normaltest.lombok.UserBean;
import sun.applet.Main;

/**
 * @author liuxi
 * @date2017年12月21日 16:43
 */
public class LambadaTest {
    List<UserBean> userBeans = new ArrayList<>();
    public LambadaTest(){
        UserBean u1 = new UserBean();
        u1.setAge(3);
        u1.setUserName("xixi");
        userBeans.add(u1);
        UserBean u2 = new UserBean();
        u2.setAge(4);
        u2.setUserName("hehe");
        userBeans.add(u2);
        UserBean u3 = new UserBean();
        u3.setAge(5);
        u3.setUserName("xixi");
        userBeans.add(u3);
        UserBean u4 = new UserBean();
        u4.setAge(4);
        u4.setUserName("hehe");
        userBeans.add(u4);
    }

    /**
     * 通过stream()把一个集合的某一个属性变成key
     *
     */
    public void testGroupBy(){
        Map<String, List<UserBean>> collect = userBeans.stream().collect(
            Collectors.groupingBy(UserBean::getUserName));
        Set<String> strings = collect.keySet();
        for (String key:
            strings) {
            List<UserBean> userBeans1 = collect.get(key);
            System.out.println("userName:"+key);
            for (UserBean userBean:
                userBeans1) {
                System.out.println(userBean.getAge());
                System.out.println(userBean.getUserName());

            }
            System.out.println("-----------");
        }
    }

    /**
     * 通过stream()把集合的属性输出到一个List里面
     */
    public void testPutFiledInList(){
        userBeans.stream().map(UserBean::getUserName).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
    public static void main(String[] args) {
        LambadaTest lambadaTest = new LambadaTest();
        lambadaTest.testGroupBy();
        lambadaTest.testPutFiledInList();

    }
}
