/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.sort;

import java.util.List;

/**
 * @author reid.liu
 * @date 2019-02-28 13:42
 */
public interface ISorter<T> {
    /**
     * 升序
     * @param field
     * @return
     */
    default  List<T> sortDesc(String field){
        return null;
    }

    /**
     * 降序
     * @param field
     * @return
     */
    default  List<T> sortAsc(String field){return null;}

    List<T> sort(String field);
    List<T> reverseSort(String field);
}
