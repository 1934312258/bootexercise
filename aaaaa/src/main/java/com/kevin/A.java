package com.kevin;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author
 * @date 2020-6-4 16:30
 * @description todo
 **/
public class A implements TestInterface {

    public void test() {
        System.out.println("a");
    }

    public A() {
        System.out.println('a');
    }

    public static final long DAY_MILLISECONDS = 24 * 3600 * 1000;

    // 一小时的毫秒数
    public static final long HOUR_MILLISECONDS = 3600 * 1000;

    // 一分钟的毫秒数
    public static final long MINUTE_MILLISECONDS = 60 * 1000;

    // 一秒的毫秒数
    public static final long SECOND_MILLISECONDS = 1000;
    String name;
    private static final int ERROR = 11111111;

    static ThreadLocal<Integer> local = new ThreadLocal<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        char ascii = ' ';
        char ascii1 = 101;
        System.out.println(ascii == 32);
        System.out.println(ascii);
        System.out.println(ascii1);

        List list = new ArrayList();
        A a1 = new A();
        A a2 = new A();
        a2.setName(a1.getName());
        a1.setName(null);

        list.add(123);
        list.add(456);
        list.add(789);
        Map<String, List<Integer>> map1 = new HashMap();
        map1.put("kevim", list);
        map1.forEach((k, v) -> {
            v = v.subList(0, 1);
            map1.put(k, v);
        });
        List list1 = list.subList(0, 1);
        List list2 = (List) list.stream().filter(a -> !list1.contains(a)).collect(Collectors.toList());


//        List<Integer> intr = list.stream().map(a->a.intValue()).collect(Collectors.toList());
        System.out.println(list.contains(123));

        Map map = new HashMap();
        map.put("kecin", "1234");
        map.put("ewr", "reg");
        map.keySet().forEach(a ->
        {
            System.out.println(map.get(a));

        });

        map.put(1, Arrays.asList(1));
        map.put(1, Arrays.asList(2));
        List list12 = (List) map.get(1);
        System.out.println();
        B b = new B();
        b.setAge(12);
        map.put(2, b);
        B b1 = (B) map.get(2);
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        Date date = new Date();
        System.out.println((new Date()).getTime());
        System.out.println(new BigDecimal(12));
        System.out.println("rgf" + b.getName());

        System.out.println(Arrays.asList(list));

        List<Integer> list3 = new ArrayList();
        list3.add(1);
        list3.add(1);
        list3.add(1);
        list3.add(2);
        list3.add(2);
        list3.add(3);
        list3.add(3);
        list3.add(4);
        Map<Integer, List<Integer>> map2 = list3.stream().collect(Collectors.groupingBy(a -> a, Collectors.mapping(a -> a, Collectors.toList())));
        System.out.println(new BigDecimal(12));
        String str = "2343690";
        System.out.println(str.indexOf("q"));

        for (Object o : map.entrySet()) {
            System.out.println();
        }
        System.out.println(list3.contains(2));
        System.out.println(ERROR == 11111111);

        List<String> list4 = new ArrayList<>();
        list4.add("11111111");
        list4.add("325436");
        Long dsf = Long.parseLong("43766487");
        List<Long> list5 = list4.stream().map(a -> Long.parseLong(a)).collect(Collectors.toList());
        System.out.println("二等分 离开你发我");
        Long time = 4905706734857643L;
        int day = (int) (time / DAY_MILLISECONDS);
        int hour = (int) ((time - DAY_MILLISECONDS * day) / HOUR_MILLISECONDS);
        int minute = (int) ((time - DAY_MILLISECONDS * day - HOUR_MILLISECONDS * hour) / MINUTE_MILLISECONDS);
        int second = (int) ((time - DAY_MILLISECONDS * day - HOUR_MILLISECONDS * hour - MINUTE_MILLISECONDS * minute) / SECOND_MILLISECONDS);
        System.out.println();
        int as = 1111;
        int asd = local.get() == null ? 0 : local.get();
        System.out.println(local.get());
        local.set(3);
        System.out.println(local.get());
        local.set(as + 1);
        System.out.println(local.get());

        Integer integer = new Integer(1111);
        System.out.println(as == integer);

        StringBuilder sb = new StringBuilder();
        sb.toString();

        map.put("rwfgr", null);
        map.put("rfeg", null);
        map.put(null, "rewdgfr");
        map.put(null, "rgthgbqt4gq");
        map.put(null, null);

        Map<Object, Object> map3 = new ConcurrentHashMap();
//        map3.put(null,null);
//        map3.put("fdagreqg",null);
//        map3.put(null,"regterh");
//        map3.put(null,"dgreqfgert");

        String excl = "^[A-Za-z0-9-._]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email = "zhaowenjian@aliyun.com";
        System.out.println(email.matches(excl));

        String s = "hyryj";
        s.split(",");
    }

    @Override
    public void test1() {

    }
}
