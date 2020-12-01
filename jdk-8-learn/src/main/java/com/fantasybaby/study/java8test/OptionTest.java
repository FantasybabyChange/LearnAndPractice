package com.fantasybaby.study.java8test;


import com.fantasybaby.study.java8test.dozer.UserDO;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author liuxi
 * @date2017年12月13日 19:33
 */
public class OptionTest {
    public static void main(String[] args) {
        Optional<String> s = Optional.of("123");

        String s1 = s.get();
        System.out.println(s1);
        System.out.println(s.isPresent());
        s.ifPresent(value -> System.out.println(value));
        //Optional<Object> o2 = Optional.of(null);
        Optional<String> newOption = s.map((a) -> a + "123");

        Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o.isPresent());
        o.ifPresent(value -> System.out.println(value));

        Object newObject = s.orElse("hehe");
        System.out.println("orElse test:" + newObject);

        UserDO user = new UserDO();

        Optional<UserDO> userOption = Optional.ofNullable(user);
        userOption.ifPresent(value ->
            System.out.println(value.getUserID())
        );

        userOption.ifPresent(new Consumer<UserDO>() {
            @Override
            public void accept(UserDO userDO) {
                System.out.println("-----accept-----");
                System.out.println(userDO.getUserID());
            }

            @Override
            public Consumer<UserDO> andThen(Consumer<? super UserDO> after) {
                return null;
            }
        });


    }
}
