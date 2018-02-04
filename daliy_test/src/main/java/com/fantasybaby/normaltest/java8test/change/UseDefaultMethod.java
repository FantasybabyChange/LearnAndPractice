package com.fantasybaby.normaltest.java8test.change;

/**
 * 通过上述代码，大家可以很清楚的发现，
 * 如果在接口中定义默认方法，则子类不需要必须实现该默认实现，如果有特殊需求或者需要，则可以Override该实现。还有一种情形，如果一个类实现两个或两个以上接口，并且多个接口中包含统一默认方法，
 * 此时，编译器将报错。这种情况，我们必须让子类Override该方法，否则无法编译通过。
 */
public class UseDefaultMethod implements  IInterfaceTest {
    @Override
    public void workHard() {
        String hehe = getName("hehe");
        System.out.printf(hehe);
    }
}
