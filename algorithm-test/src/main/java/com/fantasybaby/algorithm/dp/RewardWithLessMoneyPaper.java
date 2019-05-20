/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.*;

/**给定总金额
 * 和货币总数
 * 计算出最少的货币纸张分法
 * 使用动态规划的思想
 *  c[i]= arg min(c[i-value(j)])   j为type数量
 * @author reid.liu
 * @date 2019-05-14 19:40
 */
public class RewardWithLessMoneyPaper {

    public static int lasetMoneyCalculateDP(int amount, int[] coins){
        if(amount < 0|| coins.length == 0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }
        amount += 1;
        int[] c = new int[amount];

        for (int i = 0; i < amount; i++) {
            int best = -1;
            for (Integer typeValue : coins) {
                if(i - typeValue >= 0){
                    /**
                     * 当前无解
                     */
                    if(c[i - typeValue] == -1 && i - typeValue > 0){
                        continue;
                    }
                    int anser = c[i - typeValue];
                    if(i - typeValue ==0)anser=1;else anser+=1;
                    if(best == -1) best=anser;
                    else  best = Math.min(anser,best);

                }
            }
            c[i] = best;
        }
        System.out.println(Arrays.toString(c));
        return c[c.length - 1];
    }

    /**
     * 这个方法有问题
     * @param coins
     * @param amount
     * @return
     */
    public static int userDelie(int[] coins, int amount){
        List<Integer> ints = new ArrayList();
        for (int coin : coins) {
            ints.add(coin);
        }

        ints.sort((a,b)->{if(a >b)return -1;else return 0;});
        int lessResult = -1;
        while (ints.size() != 0){
            int result = 0;
            int mod = amount;
            for (Integer type : ints) {
                if(mod < type){
                    continue;
                }
                result += mod/type;
                mod = mod % type;
                if(mod == 0){
                    break;
                }
            }
            if(mod == 0){
                if(lessResult == -1){
                    lessResult = result;
                }else{
                    lessResult = Math.min(lessResult,result);
                }
            }
            ints.remove(0);
        }
        return lessResult;
    }
    public static int coinChange(int[] coins, int amount) {
//        Arrays.sort(coins);
        int[] ans = new int[amount + 1];
        ans[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int v = i - coins[j];
                if (v < 0) break;
                if (ans[v] != -1 && min > ans[v]) min = ans[v];
            }
            if (min == Integer.MAX_VALUE)
                ans[i] = -1;
            else
                ans[i] = min + 1;
        }
        return ans[amount];
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
        int [] lists = new int[]{1};
        int i = RewardWithLessMoneyPaper.lasetMoneyCalculateDP(0, lists);
        System.out.println(i);
        int [] array = new int[]{2,3,7};
        int [] array1 = new int[10];
        int an = RewardWithLessMoneyPaper.getMinMoney(array1, array);
        System.out.println(an + 1);
//        List types = Lists.newArrayList();
//        types.add(7);
//        types.add(2);
//        types.add(3);
        //当前数有问题
        int [] array2 = new int[]{186,419,83,408};
        int i1 = userDelie(array2, 6249);
        System.out.println(i1);
        int i2 = coinChange(array2, 6249);
        System.out.println(i2);

    }
}
