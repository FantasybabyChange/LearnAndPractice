package com.fantasybaby.study.java8test.lambadatest;

/**定义一个函数式接口
 * @author liuxi
 * @date2018年02月07日 15:34
 */
@FunctionalInterface
public interface IConvertType<F,T> {
    /**
     * 转换一个类型到另一个类型
     * @param f
     * @return
     */
    T convert(F f);
}
