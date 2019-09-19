package com.kevin.firstUtil;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author kevin
 * @date 2019-9-9 15:50
 * @description 时间工具类
 **/
public class Timeutil {
    public static void main(String[] args) {
        /*
         * LocalDate与LocalTime均为不可变量，修改之后必修赋值给新的对象引用
         * */
        String str = "2019-09-05T02:19:20.373Z";

        //获取当前年月日
        LocalDate date = LocalDate.now();

        //获取年、月、日
        int year = date.getYear();
        int month = date.getMonthValue();
        Month month1 = date.getMonth();//获取月份对象
        int day = date.getDayOfMonth();
        int d = date.getDayOfYear();
        DayOfWeek week = date.getDayOfWeek();//获取当前周几的枚举类对象的值
        //printf
//        System.out.printf("Year : %d  Month : %d  day : %d ", year, month, day);
//        System.out.println(date.getDayOfWeek());

        //获取任意时间对象
        LocalDate date1 = LocalDate.of(2019, 8, 29);

        //两个时间进行比较
        if (date1.equals(date)) {
            System.out.printf("date %s and date1 %s are same date %n", date, date1);
        }
        //比较两个日期的早晚
//        System.out.println(date.isAfter(date1));
//        System.out.println(date.isBefore(date1));

        //检查周期性事件
        LocalDate birth = LocalDate.of(1990, 2, 13);
        MonthDay birthday = MonthDay.of(birth.getMonth(), birth.getDayOfMonth());//亦可换位birth.getMonthValue()，两个重载方法
        //最简单的方法是MonthDay.from(birth)获取当前日期对象的月日
        MonthDay currentday = MonthDay.now();//MonthDay.from(date);
//        if(currentday.equals(birthday)){
//            System.out.println("Many Many happy returns of the day !!");
//        }else{
//            System.out.println("Sorry, today is not your birthday");
//        }
        YearMonth yearMonth = YearMonth.now();
        int days = yearMonth.lengthOfMonth();//本月的天数，亦可查询本年的天数
        yearMonth.atEndOfMonth();//本月在最后一天的日期

        //获取当前时间默认的时间格式为hh:mm:ss:nnn
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minuite = time.getMinute();
        int second = time.getSecond();
        int nsecond = time.getNano();
        Long times = time.getLong(ChronoField.HOUR_OF_DAY);//获取时间段内的具体值如当前秒的多少毫秒

        //给时间加上相应时间
        LocalTime newTime = time.plusHours(2);//当前时间加两个小时

        //计算一周后的日期
        LocalDate newDate = date.plus(1, ChronoUnit.WEEKS);
        //计算一周前的日期
        LocalDate lastDate = date.minus(1, ChronoUnit.WEEKS);

        //java 8增加了一个Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。

        // 以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
        Clock clock = Clock.systemDefaultZone();
        //clock.instant()获取当前日期时间，clock.millis()获取时间毫秒
        Clock clock1 = Clock.systemUTC();//世界协调时间
        LocalDate.now(clock);//获取时区的时间

        //ZoneId时区
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZoneId zone1 = ZoneId.of("America/New_York");
        LocalTime a = LocalTime.now(zone);
        LocalDate b = LocalDate.now(zone1);
        LocalDateTime c = LocalDateTime.now(zone);

        //转换时间为另一时区
        //直接加入时区即可获得相应时区的时间
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
        //获取时区内当前时间、偏移量与时区的ZonedDateTime实例
        ZonedDateTime american = ZonedDateTime.now(ZoneId.of("America/New_York"));
        //获取本地时间、时区的ZonedDateTime实例，不明白什么意思
        ZonedDateTime zonedate = ZonedDateTime.of(LocalDateTime.now(), zone);

        //计算两个日期之间间隔的天数、月数、年数
        LocalDate next = LocalDate.of(2020, 07, 19);
        Period period = Period.between(date, next);
        period.getDays();
        period.getMonths();
        period.getYears();

        System.out.println(dateTime);
        System.out.println(yearMonth.atEndOfMonth());


    }

}
