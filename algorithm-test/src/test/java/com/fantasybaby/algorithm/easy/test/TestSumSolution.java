/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.easy.test;

import com.fantasybaby.algorithm.easy.SumSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**sum solution test
 * @author reid.liu
 * @date 2019-01-11 14:48
 */
public class TestSumSolution {
    SumSolution sumSolution;
    @Before
    public void init(){
        sumSolution = new SumSolution();
    }

    @Test
    public void testSum(){
        int sum = sumSolution.sum(1, 5);
        Assert.assertEquals(6,sum);

    }

}
