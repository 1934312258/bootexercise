package com.kevin.firstUtil;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * @author kevin
 * @date 2019-9-9 15:50
 * @description 时间工具类
 **/
public class Timeutil {
    public static void main(String[] args) {
        /**
         * 通过这些例子，你肯定已经掌握了Java 8日期时间API的新知识点。现在我们来回顾一下这个优雅API的使用要点：
         *
         * 1）提供了javax.time.ZoneId 获取时区。
         *
         * 2）提供了LocalDate和LocalTime类。
         *
         * 3）Java 8 的所有日期和时间API都是不可变类并且线程安全，而现有的Date和Calendar API中的java.util.Date和SimpleDateFormat是非线程安全的。
         *
         * 4）主包是 java.time,包含了表示日期、时间、时间间隔的一些类。里面有两个子包java.time.format用于格式化， java.time.temporal用于更底层的操作。
         *
         * 5）时区代表了地球上某个区域内普遍使用的标准时间。每个时区都有一个代号，格式通常由区域/城市构成（Asia/Tokyo），在加上与格林威治或 UTC的时差。例如：东京的时差是+09:00。
         *
         * 6）OffsetDateTime类实际上组合了LocalDateTime类和ZoneOffset类。用来表示包含和格林威治或UTC时差的完整日期（年、月、日）和时间（时、分、秒、纳秒）信息。
         *
         * 7）DateTimeFormatter 类用来格式化和解析时间。与SimpleDateFormat不同，这个类不可变并且线程安全，需要时可以给静态常量赋值。 DateTimeFormatter类提供了大量的内置格式化工具，
         * 同时也允许你自定义。在转换方面也提供了parse()将字符串解析成日期，如果解析出错会抛出DateTimeParseException。DateTimeFormatter类同时还有format()用来格式化日期，如果出错会抛出DateTimeException异常。
         *
         * 8）再补充一点，日期格式“MMM d yyyy”和“MMM dd yyyy”有一些微妙的不同，第一个格式可以解析“Jan 2 2014”和“Jan 14 2014”，而第二个在解析“Jan 2 2014”就会抛异常，因为第二个格式里要求日必须是两位的。
         * 如果想修正，你必须在日期只有个位数时在前面补零，就是说“Jan 2 2014”应该写成 “Jan 02 2014”。
         */




        /*
        * LocalDate与LocalTime均为不可变量，修改之后必修赋值给新的对象引用
        * */


        //获取当前年月日
        LocalDate date = LocalDate.now();

        //获取年、月、日
        int year = date.getYear();
        int month = date.getMonthValue();
        Month month1 =date.getMonth();//获取月份对象
        int day = date.getDayOfMonth();
        int d=date.getDayOfYear();
        DayOfWeek week=date.getDayOfWeek();//获取当前周几的枚举类对象的值
        //printf
//        System.out.printf("Year : %d  Month : %d  day : %d ", year, month, day);
//        System.out.println(date.getDayOfWeek());

        //获取任意时间对象
        LocalDate date1=LocalDate.of(2019,8,29);

        //两个时间进行比较
        if(date1.equals(date)){
            System.out.printf("date %s and date1 %s are same date %n", date, date1);
        }
        //比较两个日期的早晚
//        System.out.println(date.isAfter(date1));
//        System.out.println(date.isBefore(date1));

        //检查周期性事件
        LocalDate birth=LocalDate.of(1990,2,13);
        MonthDay birthday=MonthDay.of(birth.getMonth(),birth.getDayOfMonth());//亦可换位birth.getMonthValue()，两个重载方法
        //最简单的方法是MonthDay.from(birth)获取当前日期对象的月日
        MonthDay currentday=MonthDay.now();//MonthDay.from(date);
//        if(currentday.equals(birthday)){
//            System.out.println("Many Many happy returns of the day !!");
//        }else{
//            System.out.println("Sorry, today is not your birthday");
//        }
        YearMonth yearMonth=YearMonth.now();
        int days=yearMonth.lengthOfMonth();//本月的天数，亦可查询本年的天数
        yearMonth.atEndOfMonth();//本月在最后一天的日期

        //获取当前时间默认的时间格式为hh:mm:ss:nnn
        LocalTime time=LocalTime.now();
        int hour=time.getHour();
        int minuite=time.getMinute();
        int second=time.getSecond();
        int nsecond=time.getNano();
        Long times=time.getLong(ChronoField.HOUR_OF_DAY);//获取时间段内的具体值如当前秒的多少毫秒

        //给时间加上相应时间
        LocalTime newTime=time.plusHours(2);//当前时间加两个小时

        //计算一周后的日期
        LocalDate newDate=date.plus(1, ChronoUnit.WEEKS);
        //计算一周前的日期
        LocalDate lastDate=date.minus(1,ChronoUnit.WEEKS);

        //java 8增加了一个Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。

        // 以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
        Clock clock=Clock.systemDefaultZone();
        //clock.instant()获取当前日期时间，clock.millis()获取时间毫秒
        Clock clock1=Clock.systemUTC();//世界协调时间
        LocalDate.now(clock);//获取时区的时间

        //Instant类获取时间戳,默认UTC标准时间的时间戳，
        //相当于Date类且可以转换，Date.from(instant),Date.toInstant()
        Instant instant=Instant.now();

        //ZoneId时区
        ZoneId zone=ZoneId.of("Europe/Berlin");
        ZoneId zone1=ZoneId.of("America/New_York");
        LocalTime a=LocalTime.now(zone);
        LocalDate b=LocalDate.now(zone1);
        LocalDateTime c=LocalDateTime.now(zone);

        //转换时间为另一时区
        //直接加入时区即可获得相应时区的时间
        LocalDateTime dateTime=LocalDateTime.now(ZoneId.systemDefault());
        //获取时区内当前时间、偏移量与时区的ZonedDateTime实例
        ZonedDateTime american=ZonedDateTime.now(ZoneId.of("America/New_York"));
        //获取本地时间、时区的ZonedDateTime实例，不明白什么意思
        ZonedDateTime zonedate=ZonedDateTime.of(LocalDateTime.now(),zone);
        //ZoneOffset类用于表示时区,与GMT或UTC标准时区的差异
        ZoneOffset zoneOffset=ZoneOffset.of("+02:00");
        //只显示默认时区时间与偏移量，ZonedDateTime还可以显示时区
        OffsetDateTime offsetDateTime=OffsetDateTime.of(LocalDateTime.now(),zoneOffset);




        //计算两个日期之间间隔的天数、月数、年数
        LocalDate  next=LocalDate.of(2020,07,19);
        Period period=Period.between(date,next);
        period.getDays();
        period.getMonths();
        period.getYears();


        //判断是否为闰年
        Boolean leapYear=date.isLeapYear();



        //日期字符串之间的转换
        //字符串转日期,DateTimeFormatter有已经定义好的格式，暂不熟悉，可以使用自定义格式
        LocalDate localDate=LocalDate.parse("20140112", DateTimeFormatter.BASIC_ISO_DATE);
        //自定义解析格式,MMM代表月份的****英文缩写，本机目前为中文格式
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localDate1=LocalDate.parse("九月 23 2032",formatter);

        //日期转换为字符串MMM为中文月份
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("M d yyyy hh:mm a");
        String string=dateTime.format(dateTimeFormatter);

        /////*****当不确定月份或者天有没有补零时，可以使用M d yyyy格式解析日期
        String str = "2019-09-05T02:19:20.373";
        //对于正常的时间格式如上，可直接使用parse方法解析，不需要pattern格式
        LocalDateTime.parse(str);
        //当字符串后面有Z时，需要替换掉Z,或者先使用ZonedDateTime解析再转为LocalDateTime
        String str1 = "2019-09-05T02:19:20.373Z";
        ZonedDateTime adt=ZonedDateTime.parse(str1);
        LocalDateTime ld=adt.toLocalDateTime();

        String valueIn = "2019-02-19T23:28:04.434410+0800";
        //带有时区的日期应该使用次模板转换
        String formatIn = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";


       String str2="2012/12/12";
        Calendar calendar=Calendar.getInstance();
        calendar.getTime();
        System.out.println(clock1.instant());
        System.out.println(instant);
        System.out.println(adt);



    }

}
