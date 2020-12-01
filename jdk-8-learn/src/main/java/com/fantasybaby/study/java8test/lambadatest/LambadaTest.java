package com.fantasybaby.study.java8test.lambadatest;

import com.fantasybaby.study.java8test.change.ObjectFactory;
import com.fantasybaby.study.java8test.domain.UserBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**lambada和jdk一些新的特性
 * @author liuxi
 * @date2017年12月21日 16:43
 */
public class LambadaTest {
    List<UserBean> userBeans = new ArrayList<>();
    public LambadaTest(){
        UserBean u1 = new UserBean();
        u1.setId(1);
        u1.setAge(3);
        u1.setUserName("xixi");
        userBeans.add(u1);
        UserBean u2 = new UserBean();
        u2.setId(2);
        u2.setAge(4);
        u2.setUserName("hehe");
        userBeans.add(u2);
        UserBean u3 = new UserBean();
        u3.setId(3);
        u3.setAge(5);
        u3.setUserName("xixi");
        userBeans.add(u3);
        UserBean u4 = new UserBean();
        u4.setId(4);
        u4.setAge(4);
        u4.setUserName("hehe");
        userBeans.add(u4);
        userBeans.add(u3);
        UserBean u5 = new UserBean();
        u5.setId(5);
        u5.setAge(6);
        u5.setUserName("jiji");
        userBeans.add(u5);
    }

    /**
     * 通过stream()把一个集合的某一个属性变成key
     *
     */
    public void testGroupBy(){
        Map<String, List<UserBean>> collect = userBeans.stream().collect(
            Collectors.groupingBy(UserBean::getUserName));
        Set<String> strings = collect.keySet();
        for (String key:
            strings) {
            List<UserBean> userBeans1 = collect.get(key);
            System.out.println("userName:"+key);
            for (UserBean userBean:
                userBeans1) {
                System.out.println(userBean.getAge());
                System.out.println(userBean.getUserName());

            }
            System.out.println("-----------");
        }
    }

    /**
     * 通过stream()把一个集合的某一个属性变成key
     * 然后看是否会影响之前的list
     *
     */
    public void testGroupByChangeValue(){
        Map<Integer, List<UserBean>> collect = userBeans.stream().collect(
            Collectors.groupingBy(UserBean::getId));
        Set<Integer> strings = collect.keySet();
        for (Integer key:
            strings) {
            List<UserBean> userBeans1 = collect.get(key);
            UserBean userBean = userBeans1.get(0);
            userBean.setUserName("newName");

            System.out.println("-----------");
        }
        userBeans.stream().forEach(System.out::print);
    }

    /**
     * 通过stream()把集合的属性输出到一个List里面
     */
    public void testPutFiledInList(){
        userBeans.stream().map(UserBean::getUserName).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    public void testAnyMatch(){
        List<String> names = new ArrayList<>();
        names.add("jiji");
        names.add("hehe");
        List<UserBean> matchBeans = userBeans.stream().filter(
            userBean -> names.stream().anyMatch(name -> userBean.getUserName().equalsIgnoreCase(name)))
            .collect(Collectors.toList());
        for (UserBean matchBean:
        matchBeans) {
            System.out.println(matchBean);

        }

    }
    public void testStringArrayToLong(){
        String str = "1,2,3,4,5,6";
        List<Long> longArrays = Arrays.stream(str.split(",")).map(sowTaskId -> Long.valueOf(sowTaskId))
            .collect(Collectors.toList());
        longArrays.forEach( System.out::println);
    }

    public void testLambaArr(){
        List<UserBean> u1s = new ArrayList<>();
        List<UserBean> u2s = new ArrayList<>();
        u1s.stream().forEach(u1 ->u2s.stream().forEach(u2 ->{

            })
        );
    }
    public void testNotEmpty(){
        List<String> collect = userBeans.stream().map(user -> getValue()).filter(a-> a != null).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    private static int a = 0;
    private String getValue(){
        a++;
        if(a == 3){
            return null;
        }else{
            return a+"";
        }
    }

    /**
     * jdk1.8排序练习
     */
    public  void testComparator(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, Comparator.reverseOrder());
        names.forEach(System.out::println);
    }

    /**
     * predicate使用
     * 可以用来实现判断的逻辑
     */
    public void testPredicate(){
        Predicate<String> predicate  = (a)->a.substring(0,1).equals("a");
        boolean testAnser = predicate.test("ahello");

        System.out.println(testAnser);
    }

    /**
     * function接口测试
     */
    public void testFunction(){
        Function<String,Integer> function = Integer::valueOf;
        Integer applyValue = function.apply("123");
        System.out.println(applyValue);
        Function<Integer,String> function1 = (s)->s+"hehe";
        Function<String, String> functionNew = function.andThen(function1);
        System.out.println(functionNew.apply("1231"));
    }

    /**
     * Consumer可以处理bean
     */
    public void testConsumer(){
        Consumer<UserBean> consumer = (a) -> a.setAge(10);
        UserBean userBean = ObjectFactory.create(UserBean::new);
        consumer.accept(userBean);
        System.out.println(userBean.getAge());
    }

    /**
     * reduce可以聚合当前流的元素,
     * 生成一个新的对象
     */
    public void testReduce(){
        Optional<UserBean> reduce = userBeans.stream().reduce((a,b) ->{
            UserBean userBean = ObjectFactory.create(UserBean::new);
            userBean.setAge(a.getAge()+b.getAge());
            return userBean;
        });
        reduce.ifPresent(System.out::println);
        userBeans.forEach(System.out::println );
    }
    /**
     * map可以将function的对象聚合
     */
    public void testMap(){
        this.userBeans.stream().map((a) -> a.getAge());
    }

    /**
     * 并行流测试
     * @param
     */
    public void testParallStream(){
        Optional<UserBean> reduce = userBeans.parallelStream().reduce((a,b) ->{
            UserBean userBean = ObjectFactory.create(UserBean::new);
            userBean.setAge(a.getAge()+b.getAge());
            return userBean;
        });
        reduce.ifPresent(System.out::println);
    }

    /**
     * java8 map的一些新特性
     */
    public void testNewMap(){
        Map<String,String> newMap = Maps.newHashMap();
        newMap.putIfAbsent("key1", "123");
        /**
         * 如果不存在则put value
          */
        newMap.putIfAbsent("key1","234");

        /**
         * 如果当前key有值则重新计算
         */
        newMap.computeIfPresent("key1",(a,b)->a+"--"+b);
//        newMap.computeIfPresent("key1",(a,b)->null);
        /**
         * 与computeIfPresent相反
         */
//        newMap.computeIfAbsent()
        /**
         * 如果值相等再删除
         */
//        newMap.remove("key1","key1--123");
        /**
         * 合并两个值
         */
        newMap.merge("key1","key2",(a,b)->a+b);
        newMap.forEach((a,b)-> System.out.println(a+"--"+b));
    }
    public void testSummer(){
        long summer = userBeans.stream().collect(Collectors.summingInt(UserBean::getAge));
        System.out.println(summer);
    }
    public void convertToMap(){
        Map<Integer, UserBean> collect = userBeans.stream().distinct().collect(Collectors.toMap(UserBean::getId, p -> p));
        ArrayList<Integer> ids = Lists.newArrayList(collect.keySet());
        ids.forEach(id ->{
            UserBean userBean = collect.get(id);
            System.out.println(userBean.getAge());
            System.out.println(userBean.getUserName());
        });
    }

    public void testSortWithMany(){
        userBeans.sort(Comparator.comparing(UserBean::getAge).thenComparing(UserBean::getId));
        userBeans.forEach(u -> {
            System.out.println(u.getUserName());
            System.out.println("age:"+u.getAge());
            System.out.println("id:"+u.getId());
        });
    }
    public static void main(String[] args) {
        LambadaTest lambadaTest = new LambadaTest();
        //lambadaTest.testGroupByChangeValue();
        //lambadaTest.testStringArrayToLong();
        //lambadaTest.testGroupBy();
        //lambadaTest.testPutFiledInList();
       //lambadaTest.testAnyMatch();
       // lambadaTest.testNotEmpty();
       // lambadaTest.testComparator();
       // lambadaTest.testPredicate();
        //lambadaTest.testFunction();
//        lambadaTest.testConsumer();
//        lambadaTest.testReduce();
//        lambadaTest.testParallStream();
//           lambadaTest.testNewMap();
//        lambadaTest.testSummer();
//        lambadaTest.convertToMap();
        lambadaTest.testSortWithMany();
    }


}
