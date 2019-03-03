/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.sort;

import com.fantasybaby.algorithm.util.FiledValueHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import lombok.Data;
import lombok.ToString;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author reid.liu
 * @date 2019-03-03 14:31
 */
public class SortByGroupSorter {
    private List<SortMappingObject> sortObjects = Lists.newArrayList();
    private Map<Integer,SortObject> map = Maps.newHashMap();
    private List<SortObject> sorts;
    @Data
    @ToString
    class SortMappingObject{
        private int id;
        private int group;
    }
    @Data
    @ToString
    class SortObject{
        private int id;
        private String name;
        private int age;
        private String order;
    }
    public  void sort(){
        initData();
        /*sortObjects.sort((a,b)->{
            SortObject o1 = map.get(a.getId());
            SortObject o2 = map.get(b.getId());
            System.out.println(o1+":"+o2);
            if(o1.getAge() > 6 ){
                b.setGroup(a.getGroup());
                return 1;
            }else{
                b.setGroup(a.getGroup());
                return -1;
            }

        });*/
        boolean firstFlag = true;
        List<Pair> fileds = Lists.newArrayList();
        Pair<String,String> f1 = new Pair<>("age","");
        Pair<String,String> f2 = new Pair<>("name","DESC");
        fileds.add(f1);
        fileds.add(f2);
        sorts.sort((a,b)->{
            int ret = 0;
            for (Pair result : fileds) {
                Object af1 = FiledValueHelper.getFieldByClasss((String) result.getKey(), a);
                Object af2 = FiledValueHelper.getFieldByClasss((String) result.getKey(), b);
                String str1 = af1.toString();
                String str2 = af2.toString();

                if (af1 instanceof Number && af1 instanceof Number) {
                    int maxlen = Math.max(str1.length(), str2.length());
                    str1 = addZero2Str((Number) af1, maxlen);
                    str2 = addZero2Str((Number) af2, maxlen);
                } else if (af1 instanceof Date && af2 instanceof Date) {
                    long time1 = ((Date) af1).getTime();
                    long time2 = ((Date) af2).getTime();
                    int maxlen = Long.toString(Math.max(time1, time2)).length();
                    str1 = addZero2Str(time1, maxlen);
                    str2 = addZero2Str(time2, maxlen);
                }
                ret = str1.compareTo(str2);
                if(ret == 0){
                    continue;
                }
                if("DESC".equals(result.getValue())){
                    return -ret;
                }
                return ret;
            }
            return ret;
        });

        /*for (int i = 0; i <= 1; i++) {
            if(firstFlag){
                comparing = Comparator.comparing(a -> FiledValueHelper.getFieldByClasss("age", a));
            }else{
                comparing.thenComparing(a ->  FiledValueHelper.getFieldByClasss("id", a));
            }
            if(firstFlag){
                comparing.reversed();
            }
            firstFlag = false;

        }*/
//        List<SortObject> collect = sorts.stream().sorted(sortObjectComparator).collect(Collectors.toList());
        sorts.stream().forEach(System.out::println);
    }
    /**
     * 给数字对象按照指定长度在左侧补0.
     *
     * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
     *
     * @param numObj
     *            数字对象
     * @param length
     *            指定的长度
     * @return
     */
    public static String addZero2Str(Number numObj, int length) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(length);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(length);
        return nf.format(numObj);
    }
    public void initData(){
        sorts = Lists.newArrayList();
        SortObject sortObject = new SortObject();
        sortObject.setId(1);
        sortObject.setAge(12);
        sortObject.setName("aello");
        sortObject.setOrder("abc123");

        SortObject sortObject1 = new SortObject();
        sortObject1.setId(2);
        sortObject1.setAge(6);
        sortObject1.setName("chell");
        sortObject1.setOrder("abc1231");

        SortObject sortObject2 = new SortObject();
        sortObject2.setId(3);
        sortObject2.setAge(15);
        sortObject2.setName("hel");
        sortObject2.setOrder("ab421");

        SortObject sortObject3 = new SortObject();
        sortObject3.setId(4);
        sortObject3.setAge(6);
        sortObject3.setName("helaa");
        sortObject3.setOrder("ab421cc");
        sorts.add(sortObject);
        sorts.add(sortObject1);
        sorts.add(sortObject2);
        sorts.add(sortObject3);
        map = sorts.stream().collect(Collectors.toMap(SortObject::getId,p->p));
        int i = 1;
        for (SortObject object : sorts) {
            SortMappingObject so = new SortMappingObject();
            so.setId(object.getId());
            so.setGroup(i);
            sortObjects.add(so);
            i++;
        }
    }

    public static void main(String[] args) {
        new SortByGroupSorter().sort();
    }
}
