package com.fantasybaby.algorithm.combination;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 抽奖问题
 * 从n个人里面抽1个1等奖
 * 3个2等奖
 * 10个3等奖
 *
 * 数字结果太大了
 * 变成10个里面
 * 抽1  2 3
 * 2019/4/30
 * fantasybaby
 **/
public class LottertyDraw {
    public static Map<String,Integer> rewards = new LinkedHashMap(){
        {
            put("一等奖",1);
            put("二等奖",2);
            put("三等奖",2);
        }
    };
    public static ArrayList<String> keys = new ArrayList(){{
        add("一等奖");
        add("二等奖");
        add("三等奖");
    }};
    public static ArrayList<String> people = new ArrayList(){{
        add("小1");
        add("小2");
        add("小3");
        add("小4");
        add("小5");
//        add("小6");
//        add("小7");
//        add("小8");
//        add("小9");
//        add("小10");
    }};
    public void startGenerate(){
        Set<String> keys = rewards.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key1 = iterator.next();
            Integer integer = rewards.get(key1);
            ArrayList<String> results = Lists.newArrayList();
//            combine(people,results,integer,key1);
        }
    }
    Integer count = 0;
    /**
     *
     * @param teams
     * @param result
     * @param m
     */
    public  void combine(ArrayList<String> teams, ArrayList<String> allResult,ArrayList<String> result, int m,ArrayList<String> keys) {
            if (result.size() == m){
                allResult.addAll(result);
                ArrayList<String> clone = (ArrayList<String>) allResult.clone();
                if(keys.size() == 0){
                    count++;
                    System.out.println(clone.toString());
                    System.out.println(count);
                    return;
                }else{
                    String next = keys.get(0);
                    clone.add(next);
//                    System.out.println(next);
                    Integer integer = rewards.get(next);
                    ArrayList<String> newKeys = (ArrayList<String>) keys.clone();
                    newKeys.remove(0);
                    combine(teams,clone,new ArrayList(),integer,newKeys);
                    return;
                }
            }
        for (int i = 0; i < teams.size(); i++) {
//            System.out.println(teams.get(i)+"---");

            ArrayList<String> newArray = (ArrayList<String>) result.clone();
            newArray.add(teams.get(i));

            ArrayList<String> newArrayLists = (ArrayList<String>) teams.clone();
            newArrayLists.remove(i);
//            ArrayList<String> newArrayLists = Lists.newArrayList(teams.subList(i + 1, teams.size()));
            combine(newArrayLists, (ArrayList<String>) allResult.clone(),newArray,m, keys);
        }
    }

    public static void main(String[] args) {
        System.out.println(keys.get(0));
        new LottertyDraw().combine(people,new ArrayList<>(),new ArrayList<>(),0,keys);
    }
}
