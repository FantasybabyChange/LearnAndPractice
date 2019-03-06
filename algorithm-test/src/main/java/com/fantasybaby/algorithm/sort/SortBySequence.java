/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.sort;

import com.fantasybaby.algorithm.util.FiledValueHelper;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**按照给定顺序排序
 * @author reid.liu
 * @date 2019-02-28 12:21
 */
public class SortBySequence {
    private List<String> sequence = new ArrayList(){
        {
            add("C");
            add("B");
            add("D");
            add("A");
        }
    };
    public SortBySequence(){
        initData();
    }
    @Getter
    private List<SequenceBean> beans = Lists.newArrayList();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class SequenceBean{
        private String name;
        private String type;
    }
    private void initData(){
        beans.add(new SequenceBean("ha","A"));
        beans.add(new SequenceBean("ha1","B"));
        beans.add(new SequenceBean("ha2","C"));
        beans.add(new SequenceBean("ha3","D"));
        beans.add(new SequenceBean("ha4","D"));
        beans.add(new SequenceBean("ha5","B"));
        beans.add(new SequenceBean("ha6","A"));
    }
    private List<SequenceBean>  sortBySequence(){
        ISorter<SequenceBean> sorter = new SortBySequenceSorter(sequence,beans);
        return sorter.sort("type");

    }

    public static void main(String[] args) {
        /*List<SequenceBean> sequenceBeans = new SortBySequence().sortBySequence();
        sequenceBeans.forEach(System.out::println);*/
        int a =-53 >> 1 ;
        System.out.println(a +"");
        SequenceBean sb = new SequenceBean(null,null);
        FiledValueHelper.setFieldValueByFieldName("name","123",sb);
        System.out.println(sb);


    }

}
