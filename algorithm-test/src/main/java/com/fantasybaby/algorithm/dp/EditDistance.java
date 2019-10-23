package com.fantasybaby.algorithm.dp;

import java.util.Objects;

/**
 *  动态规划求编辑距离
 * 		B empty	  m	            o	           u	            s               	e
 * empty 0	      1	            2	          3	                4	                5
 * 	m	 1	Min(2,2,0)=0	Min(3,1,2)=1	Min(4,2,3)=2	Min(5,3,4)=3	Min(6,4,5)=4
 * 	o	 2	Min(1,3,2)=1	Min(2,2,0)=0	Min(3,1,2)=1	Min(4,2,3)=2	Min(5,3,4)=3
 * 	u	 3	Min(2,4,3)=2	Min(1,3,2)=1	Min(2,2,0)=0	Min(3,1,2)=1	Min(4,2,3)=2
 * 	u	 4	Min(3,5,4)=3	Min(2,4,3)=2	Min(1,3,1)=1	Min(2,2,1)=1	Min(3,2,2)=2
 * 	s	 5	Min(4,6,5)=4	Min(3,5,4)=3	Min(2,4,3)=2	Min(2,3,1)=1	Min(3,2,2)=2
 * 	e	 6	Min(5,7,6)=5	Min(4,6,5)=4	Min(3,5,4)=3	Min(2,4,3)=2	Min(3,3,1)=1
 * 	状态方程 min(m[i-1][j]+1,m[i][j-1]+1,m[i-1][j-1]+r(i,j))
 * @author: liuxi
 * @time: 2019/5/7 23:59
 */
public class EditDistance {
    static  int getStrByDP(String source,String target){
        if(Objects.isNull(source) || Objects.isNull(target)){
            return -1;
        }
        int i = source.length();
        int j = target.length();
        int[][] distances = new int[i+1][j+1];
        distances[0][0] = 0;
        /**
         * 初始化二维数组的0位置
         */
        for (int start = 0; start <= j; start++) {
            distances[0][start] = start;
        }
        for (int start = 0; start <= i; start++) {
            distances[start][0] = start;
        }
        for (int ai = 0; ai < i ; ai++) {
            for (int bj = 0; bj < j ; bj++) {
                int result = 0;
                char c1 = source.charAt(ai);
                char c = target.charAt(bj);
                if(c1 != c){
                    //不相等编辑距离加1
                    result = 1;
                }
                int rb2a = distances[ai][bj+1]+1;
                int ra2b = distances[ai+1][bj]+1;
                int min = Math.min(rb2a, ra2b);
                int replace = distances[ai][bj] +result;
                System.out.println(rb2a + "--" + ra2b + "--" + replace);
                distances[ai+1][bj+1] = Math.min(min,replace);
            }
        }
        return distances[i][j];
    }

    /**
     * @param a- 第一个字符串，b- 第二个字符串
     * @return int- 两者之间的编辑距离
     * @Description: 使用状态转移方程，计算两个字符串之间的编辑距离
     */

    public static int getStrDistance(String a, String b) {

        if (a == null || b == null) return -1;
        // 初始用于记录化状态转移的二维表
        int[][] d = new int[a.length() + 1][b.length() + 1];

        // 如果 i 为 0，且 j 大于等于 0，那么 d[i, j] 为 j
        for (int j = 0; j <= b.length(); j++) {
            d[0][j] = j;
        }

        // 如果 i 大于等于 0，且 j 为 0，那么 d[i, j] 为 i
        for (int i = 0; i <= a.length(); i++) {
            d[i][0] = i;
        }
        // 实现状态转移方程
        // 请注意由于 Java 语言实现的关系，代码里的状态转移是从 d[i, j] 到 d[i+1, j+1]，而不是从 d[i-1, j-1] 到 d[i, j]。本质上是一样的。
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                int r = 0;
                if (a.charAt(i) != b.charAt(j)) {
                    r = 1;
                }
                int first_append = d[i][j + 1] + 1;
                int second_append = d[i + 1][j] + 1;
                int replace = d[i][j] + r;

                int min = Math.min(first_append, second_append);
                System.out.println(first_append+"---"+second_append+"--"+replace);
                min = Math.min(min, replace);
                d[i + 1][j + 1] = min;

            }
        }

        return d[a.length()][b.length()];

    }

    public static void main(String[] args) {
        int strDistance = EditDistance.getStrDistance("mous", "mouus");
        System.out.println(strDistance+"--------");
        int strDistance1 = EditDistance.getStrByDP("mous", "mouus");
        System.out.println(strDistance1);

    }
}


