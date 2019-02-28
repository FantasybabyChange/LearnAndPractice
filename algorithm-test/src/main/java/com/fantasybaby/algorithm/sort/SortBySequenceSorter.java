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
public class SortBySequenceSorter<T> implements  ISorter{
    private List<String> sequence ;
    private List<T> sortObjects;
    public SortBySequenceSorter(List<String> sequence, List<T> sortObjects){
        this.sequence = sequence;
        this.sortObjects = sortObjects;
    }
    @Override
    public List reverseSort(String field) {
        sortObjects.sort( (o1, o2) -> sequence.indexOf(FiledValueHelper.getFieldByClasss(field,o1)) > sequence.indexOf(FiledValueHelper.getFieldByClasss(field,o2))?-1:1);
        return sortObjects;
    }

    @Override
    public List sort(String field) {
        sortObjects.sort( (o1, o2) -> sequence.indexOf(FiledValueHelper.getFieldByClasss(field,o1)) > sequence.indexOf(FiledValueHelper.getFieldByClasss(field,o2))?1:-1);
        return sortObjects;
    }
}
