package com.fantasybaby.algorithm.induction;

import lombok.Data;

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

    public static void main(String[] args) {
        Result result = new Result();
        new GeometricalSequence().prove(63,result);
        System.out.println(result.getResultNum()+":"+result.getWhiteNum());
        double v = Math.pow(2, 63) - 1L;
        System.out.println(v);
    }
}
    @Data
    class Result{
        private long whiteNum;
        private long resultNum;
    }
