package com.fantasybaby.algorithm.combination;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 从几个队伍里面取出一定
 * 2019/4/30
 * fantasybaby
 **/
public class CombinationShow {
    /**
     * 求组合数
     *
     * @param n
     * @param r
     * @return
     */
    static int c(int n, int r) {
        if (r > n) {
            return 0;
        }
        int R = n - r;
        int ret = 1;
        while (n > R) {
            ret *= n--;
        }
        while (r > 1) {
            ret /= r--;
        }
        return ret;
    }

    /**
     * 求组合情况
     * @param es
     * @param r
     * @param I 数组es开始取数位置
     * @return
     */
    static int[][] C(int[] es, int r, int I) {
        int[][] rst = new int[c(es.length - I, r)][];
        if (r == 1) {
            for (int rsti = 0; rsti < rst.length; rsti++, I++) {
                rst[rsti] = new int[] { es[I] };
            }
        } else {
            for (int rsti = 0; I < es.length; I++) {
                int[][] srst = C(es, r - 1, I + 1);
                for (int[] sc : srst) {
                    int[] t = rst[rsti] = new int[sc.length + 1];
                    t[0] = es[I];
                    System.arraycopy(sc, 0, t, 1, sc.length);
                    rsti++;
                }
            }
        }
        return rst;
    }
    /**
     *
     * @param teams 所有的球队
     * @param result  组合结果
     * @param m  需要组合的数量
     */
    public void combinate(ArrayList<String> teams,ArrayList<String> result,int m){
        if(m == result.size()){
            System.out.println(result.toString());
            return;
        }
        for (int i = 0; i < teams.size() ; i++) {
            ArrayList newList = (ArrayList) result.clone();
            newList.add(teams.get(i));
            /**
             * 一定要把之前的删掉  不然会有重复的
             */
            ArrayList<String> clone = Lists.newArrayList( teams.subList(i + 1, teams.size()));
            combinate(clone,newList,m);
        }

    }


    /**
     * @Description:	使用函数的递归（嵌套）调用，找出所有可能的队伍组合
     * @param teams- 目前还剩多少队伍没有参与组合，result- 保存当前已经组合的队伍
     * @return void
     */

    public  void combine(ArrayList<String> teams, ArrayList<String> result, int m) {

        // 挑选完了 m 个元素，输出结果
        if (result.size() == m) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < teams.size(); i++) {
            System.out.println(teams.get(i));
            // 从剩下的队伍中，选择一队，加入结果
            ArrayList<String> newResult = (ArrayList<String>)(result.clone());
            newResult.add(teams.get(i));

            // 只考虑当前选择之后的所有队伍
            ArrayList<String> rest_teams = new ArrayList<String>(teams.subList(i + 1, teams.size()));

            // 递归调用，对于剩余的队伍继续生成组合
            combine(rest_teams, newResult, m);
        }

    }

    public static void main(String[] args) {
        ArrayList<String> teams = Lists.newArrayList();
        teams.add("中国");
        teams.add("巴西");
        teams.add("法国");
        teams.add("日本");
        teams.add("意大利");
        new CombinationShow().combinate(teams,Lists.newArrayList(),2);
        /*int[][] c = C(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 3, 0);
        for (int[] cc : c) {
            System.out.println(Arrays.toString(cc));
        }*/
//        new CombinationShow().combine(teams,Lists.newArrayList(),2);
    }
}
