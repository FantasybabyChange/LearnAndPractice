package com.fantasybaby.algorithm.easy;

import com.fantasybaby.algorithm.interative.SquareRoot;

import java.util.ArrayList;
import java.util.List;

/** a+b
 * @author reid.liu
 * @date 2019-01-11 14:38
 */
public class SumSolution extends SquareRoot {
    // 主要利用异或运算来完成
    // 异或运算有一个别名叫做：不进位加法
    // 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
    // 然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
    // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
    // 之后的结果。所以：a + b = (a ^ b) + (a & b << 1)
    // 令a' = a ^ b, b' = (a & b) << 1
    // 可以知道，这个过程是在模拟加法的运算过程，进位不可能
    // 一直持续，所以b最终会变为0。因此重复做上述操作就可以
    // 求得a + b的值。
    public int sum(int a ,int b){
        while (b != 0){
            int _a = a ^ b;
            int _b = (a & b)<<1;
            a = _a;
            b = _b;
        }
        return a;
    }
    public int aplusb(int a, int b) {
        // write your code here
        int m_ay = a & b;
        int m_yh = a ^ b;

        while(m_ay > 0) {
            int t_a = m_yh;
            int t_b = m_ay << 1;

            m_ay = t_a & t_b;
            m_yh = t_a ^ t_b;
        }
        return m_yh;
    }


    public static void main(String[] args) {


    }
}
