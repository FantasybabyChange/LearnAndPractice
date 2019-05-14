/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.dp;

import java.util.List;
import java.util.Objects;

/**给定总金额
 * 和货币总数
 * 计算出最少的货币纸张分法
 * 使用动态规划的思想
 * @author reid.liu
 * @date 2019-05-14 19:40
 */
public class RewardWithLessMoneyPaper {

    public int lasetMoneyCalculate(int totalMoney, List<Integer> moneyType){
        if(Objects.isNull(moneyType) || moneyType.size()==0){
            return 0;
        }
        moneyType.sort(Integer::compareTo);
//        int
        int[][]result = new int[totalMoney][moneyType.size()];
        for (int i = 1; i <= totalMoney; i++) {
            /*if(){

            }*/
        }
        return 0;

//        return result[result.length];
    }
}
