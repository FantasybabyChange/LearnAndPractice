/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**给定总金额
 * 和货币总数
 * 计算出最少的货币纸张分法
 * 使用动态规划的思想
 *  c[i]= arg min(c[i-value(j)])   j为type数量
 * @author reid.liu
 * @date 2019-05-14 19:40
 */
public class RewardWithLessMoneyPaper {

    public static int lasetMoneyCalculateDP(int totalMoney, List<Integer> moneyType){
        if(Objects.isNull(moneyType) || moneyType.size()==0){
            return 0;
        }
        totalMoney += 1;
        int[] c = new int[totalMoney];

        for (int i = 0; i < totalMoney; i++) {
            int best = 0;
            for (Integer typeValue : moneyType) {
                if(i - typeValue >= 0){
                    if(c[i - typeValue] == 0 && i - typeValue > 0){
                        continue;
                    }
                    int anser = c[i - typeValue]+1;
                    if(best == 0) best=anser;
                    else  best = Math.min(anser,best);

                }
            }
            c[i] = best;
        }
        System.out.println(Arrays.toString(c));
        return c[c.length - 1];
    }


    public static int getMinMoney(int[] c, int[] value) {
        int[] t = new int[3];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < value.length; j++) {
                if (i - value[j] >= 0) {
                    t[j] = c[i - value[j]] + 1;
                }
            }
            int min = Math.min(t[0], t[1]);
            min = Math.min(min, t[2]);
            c[i] = min;
        }
        System.out.println(Arrays.toString(c));
        return c[c.length - 1];
    }

    public static void main(String[] args) {
        List<Integer> lists = Lists.newArrayList();
        lists.add(2);
        lists.add(3);
        lists.add(7);
        int i = RewardWithLessMoneyPaper.lasetMoneyCalculateDP(10, lists);
        System.out.println(i);
        int [] array = new int[]{2,3,7};
        int [] array1 = new int[10];
        int an = RewardWithLessMoneyPaper.getMinMoney(array1, array);
        System.out.println(an + 1);
    }
}
