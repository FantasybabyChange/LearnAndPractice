package com.fantasybaby.algorithm.induction;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**使用数学归纳法 用递归替代迭代法证明
 * 2^0+2^1+2^2+...+2^n = 2^n - 1
 * @author: liuxi
 * @time: 2019/3/15 20:19
 */
public class GeometricalSequence {


    /**
     * 求证2^0+2^1+2^2+...+2^n=x^n -1
     * @param result
     * @return
     */
    public boolean prove(int n,Result result){
        if(n == 1){
            //证明当n=1时 2^1-1  = 2^0
            if(Math.pow(2,1)- 1 == 1){
                result.setResultNum(1);
                result.setWhiteNum(1);
                return true;
            }else return false;
        }else{
            /**
             * 证明n=k-1 是否成立
             */
            boolean proveOfPre = prove(n - 1, result);
            result.setWhiteNum(result.getWhiteNum()*2);
            result.setResultNum(result.getResultNum()+result.getWhiteNum());
            System.out.println("n:"+n+"_"+result.getResultNum()+":"+result.getWhiteNum());
            boolean prove = false;
            /**
             * 证明n=k是否成立
             */
            if(result.getResultNum() == (Math.pow(2,n) -1)) prove = true;
            if(prove && proveOfPre)return  true;else return  false;
        }
    }

    /**
     * 求出1 2 5 10 等数据 能有多少种形式拼接成给定数字
     */
    List<Integer> moneyType = Lists.newArrayList(1,2,5,10);

    public void listReward(int reward,ArrayList<Integer> results){
        if(reward == 0){
            System.out.println(results);
            return;
        }else if(reward < 0){
            return;
        }else{
            for (Integer money : moneyType) {
                int reset = reward - money;
                ArrayList clone = (ArrayList) results.clone();
                clone.add(money);
                listReward(reset,clone);
            }
        }

    }
    /**
     * 给定数字n 列举出 它所有的因数组合
     * 特殊判断%1的情况 不然会stack溢出
     * @param n
     * @param results
     */
    public void listFactorization(int n, ArrayList<Integer> results){
        if(n == 1){
            results.add(1);
            System.out.println(results);
            return;
        }
        for (int i = 1; i < n+1; i++) {
            if(i == 1 && results.contains(1)) {
                continue;
            }
            ArrayList<Integer> newList = (ArrayList<Integer>) results.clone();
            newList.add(i);
            if(n%i!=0)continue;
            listFactorization(n/i, newList);
        }
    }
    public static void main(String[] args) {
        /*Result result = new Result();
        new GeometricalSequence().prove(63,result);
        System.out.println(result.getResultNum()+":"+result.getWhiteNum());
        double v = Math.pow(2, 63) - 1L;
        System.out.println(v);*/
        ArrayList list = new ArrayList();
        new GeometricalSequence().listFactorization(10,list);
        System.out.println(list);
//        new GeometricalSequence().listReward(10,list);
    }
}
    @Data
    class Result{
        private long whiteNum;
        private long resultNum;
    }
