package com.fantasybaby.ireport;

import com.fantasybaby.file.freemarker.UserBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author reid.liu
 * @date 2018-10-30 17:59
 */
public class DataFactory {
    public static Collection<MainData> generateIreportData() {
        List<MainData> lists = new ArrayList<>();
        MainData o2 = new MainData();
        o2.setBarCode("12S20190907010039$4000020-BN19");
        o2.setState("true");
        o2.setCode1("1231a");
        o2.setCode2("1231b");
        List<UserBean> lists2 = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            UserBean userBean2 = new UserBean();
            userBean2.setAddress("上海虹桥"+j);
            userBean2.setUserAge(13);
            userBean2.setUserName("中华田园犬"+j);
            lists2.add(userBean2);

        }
        o2.setItems(lists2);
        lists.add(o2);
        return lists;
    }
    public static Collection<UserBean> generateUserBean() {
        List<UserBean> lists2 = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            UserBean userBean2 = new UserBean();
            userBean2.setAddress("上海虹桥"+j);
            userBean2.setUserAge(13);
            userBean2.setUserName("中华田园犬"+j);
            lists2.add(userBean2);
        }
        return lists2;
    }

}
