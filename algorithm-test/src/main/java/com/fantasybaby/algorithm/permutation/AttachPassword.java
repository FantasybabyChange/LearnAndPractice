package com.fantasybaby.algorithm.permutation;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * 假设有一个 4 位字母密码，每位密码是 a～e 之间的小写字母。你能否编写一段代码，
 * 来暴力破解该密码？（提示：根据可重复排列的规律，生成所有可能的 4 位密码
 */
public class AttachPassword{
    private static String password = "adbc";
    private static String[] words = new String[]{"a","b","c","d","e"};
    public void tryPassword( ArrayList<String> result){
        if(result.size() == 4){
            String s = String.join("",result);
            if(s.equals(password)){
                System.out.println("密码蒙对了:"+s);
            }
            return;
        }
        for (String word : words) {
            ArrayList newLists = (ArrayList) result.clone();
            newLists.add(word);
            tryPassword(newLists);
        }

    }

    public static void main(String[] args) {
        new AttachPassword().tryPassword(Lists.newArrayList());

    }


}