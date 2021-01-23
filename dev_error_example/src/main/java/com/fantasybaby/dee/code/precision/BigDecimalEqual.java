package com.fantasybaby.dee.code.precision;

import java.math.BigDecimal;

/** BigDecimal 的 equals 方法的注释中说明了原因，
 * equals 比较的是 BigDecimal 的 value 和 scale，
 * 所以1和1.0 equal是false
 * @author fanta
 * @Description
 * @create 2021-01-23 16:17
 */
public class BigDecimalEqual {
    public void notEqual(){
        System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1")));
    }
    public static void main(String[] args) {
        new BigDecimalEqual().notEqual();
    }
}
