package com.fantasybaby.normaltest.java8test.change;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

/**
 * Created by fantasybaby on 18-2-11
 */
public class NewDateTest {
    /**
     * Clock 可以拿到当前的毫秒数
     * 并且可以转换成Date类型
     * test clock
     *
     */
    public void testClock(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Date dateFromClock = Date.from(clock.instant());

    }

    /**时区类可以用一个ZoneId来表示。时区类的对象可以通过静态工厂方法方便地获取
     * 时区类还定义了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进行转换
     *TimeZone
     */
    public void testTimeZone(){
//        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        System.out.println("-"+zone1.getRules());
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone2.getRules());
    }

    /**
     * localDateTime相关测试
     */
    public void testLockTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2018, Month.FEBRUARY, 15, 16, 30);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        int dayOfMonth = localDateTime.getDayOfMonth();
        System.out.println(dayOfMonth);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
    }

    /**
     * DateTimeFormatter 是线程安全的
     * local Date只能转换时间
     *
     */
    public void testDateFormat(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse("2018-02-15 19:00:01", formatter);
        Date date = new Date();

//        System.out.println(parsed.g);
        String string = formatter.format(parsed);
        System.out.println(string);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        这里回报错  LocalDateTime parsed1 = LocalDateTime.parse("2018-02-15", formatter1);
        LocalDate parse = LocalDate.parse("2018-02-15", formatter1);
        String localDateFormate = formatter1.format(parse);
        System.out.println(localDateFormate);
    }
    public static void main(String[] args) {
        NewDateTest newDateTest = ObjectFactory.create(NewDateTest::new);
//        newDateTest.testClock();
//        newDateTest.testTimeZone();
//        newDateTest.testLockTime();
        newDateTest.testDateFormat();
    }
}
