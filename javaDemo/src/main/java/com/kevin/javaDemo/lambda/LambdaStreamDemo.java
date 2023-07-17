package com.kevin.javaDemo.lambda;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ObjectUtils;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2020-7-2 10:47
 * @description todo
 **/
public class LambdaStreamDemo {
    private List<People> peoples = null;
    List<Integer> list;
    // 1 2 3 44
    @Before
    public void before() {
        peoples = new ArrayList<>();
        peoples.add(new People("K.O1", 21, "kevin", new Date()));
        peoples.add(new People("K.O3", 23, "kevin1", new Date()));
        peoples.add(new People("K.O4", 24, "kevin", new Date()));
        peoples.add(new People("K.O5", 25, "kevin1", new Date()));
        peoples.add(new People("K.O2", 22, "kevin", new Date()));
        peoples.add(new People("K.O6", 26, "kevin2", new Date()));
//        peoples.add(new People("K.O1", 21, "kevin",new Date()));
//        peoples.add(new People("K.O3", 26, "kevin1",new Date()));
//        peoples.add(new People("K.O4", 27,"kevin", new Date()));
//        peoples.add(new People("K.O5", 28,"kevin1", new Date()));
//        peoples.add(new People("K.O2", 29, "kevin",new Date()));
//        peoples.add(new People("K.O6", 28, "kevin2",new Date()));
//        peoples.add(new People("K.O1", 33, "kevin",new Date()));
//        peoples.add(new People("K.O3", 36, "kevin1",new Date()));
//        peoples.add(new People("K.O4", 37,"kevin", new Date()));
//        peoples.add(new People("K.O5", 38,"kevin1", new Date()));
//        peoples.add(new People("K.O2", 39, "kevin",new Date()));
//        peoples.add(new People("K.O6", 38, "kevin2",new Date()));

        list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 13, 14, 23, 11, 100, 98, 23);
    }

    /**
     * 提取1列
     */
    @Test
    public void whenExtractColumnSuccess() {
        //第一种写法
        List<Integer> ages1 = peoples.stream().map(people -> people.getAge()).collect(Collectors.toList());
        System.out.println("###println: args1----");
        ages1.forEach(System.out::println);

        //简单一点的写法
        List<Integer> ages2 = peoples.stream().map(People::getAge).collect(Collectors.toList());
        System.out.println("###println: args2----");
        ages1.forEach(System.out::println);
    }

    /**
     * 过滤 filter
     * 只要年纪大于25岁的人
     */
    @Test
    public void whenFilterAgeGT25Success() {
        List<People> peoples1 = peoples.stream().filter(x -> x.getAge() > 25).collect(Collectors.toList());
        peoples1.forEach(x -> System.out.println(x.toString()));
    }

    /**
     * 取出年纪为25岁的人
     */
    @Test
    public void extractAgeEQ25Success() {
        Optional<People> optionalPeople = peoples.stream().filter(x -> x.getAge() == 25).findFirst();
        if (optionalPeople.isPresent()) System.out.println("###name1: " + optionalPeople.get().getName());

        //简写
        peoples.stream().filter(x -> x.getAge() == 25).findFirst().ifPresent(x -> System.out.println("###name2: " + x.getName()));
    }

    /**
     * 1.
     * 逗号拼接全部名字
     */
    @Test
    public void printAllNameSuccess() {
        String names = peoples.stream().map(People::getName).collect(Collectors.joining(","));
        System.out.println(names);
    }

    /**
     * 将集合转成(name, age) 的map,Map<String, Map<String, Integer>>
     */
    @Test
    public void list2MapSuccess() {
//        Map<String, Integer> map1 = peoples.stream().collect(Collectors.toMap(People::getName, People::getAge));
//        map1.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("--------");
        System.out.println(111111);

        List<Integer> ages = peoples.stream().filter(people -> !ObjectUtils.isEmpty(people.getAge())).map(People::getAge).collect(Collectors.toList());
        System.out.println(ObjectUtils.isEmpty(ages));
        Map<String, People> map3 = peoples.stream().collect(Collectors.toMap(People::getId, People -> People));

        Map<Integer, Long> map = peoples.stream().collect(Collectors.groupingBy(People::getAge, Collectors.counting()));


        Map<String, Map<String, Integer>> result =
                peoples.stream()
                        .collect(Collectors.groupingBy(People::getName,
                                Collectors.toMap(People::getId,
                                        a -> a.getAge())));
        System.out.println(result);
    }

    /**
     * 按名字分组
     */
    @Test
    public void listGroupByNameSuccess() {
        //添加一个元素方便看效果
//        peoples.add(new People("K.O1", 29,"kevin",new Date()));
        Map<String, List<People>> map = peoples.stream().collect(Collectors.groupingBy(People::getId));

        Map<String, People> peopleMap = peoples.stream().collect(Collectors.toMap(People::getId, people -> people));
        map.forEach((k, v) -> System.out.println(k + ":" + v.size()));
    }

    /**
     * 使用模糊条件查询,使用Predicate包装具体的参数
     ***/
    @Test
    public void testrandom() {
        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };
        peoples.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 按年龄排序
     */
    @Test
    public void sortByAgeSuccess() {
        System.out.println("###排序前---");
        peoples.forEach(x -> System.out.println(x.getAge()));
        // 从小到大排列
        peoples.sort((x, y) -> {
            if (x.getAge() > y.getAge()) {
                return 1;
            } else if (x.getAge() == y.getAge()) {
                return 0;
            }
            return -1;
        });

        System.out.println("###排序后---");
        peoples.forEach(x -> System.out.println(x.getAge()));
    }

    /**
     * 并行流
     ***/
    @Test
    public void testParallel() {
        peoples.stream()
                .parallel()
                .filter(a -> a.getAge() == 23)
                .forEach(System.out::println);
    }

    /**
     * 降维，例如多维数组变为一维数组
     ***/
    @Test
    public void changeTest() {
        peoples.stream()
                .map(a -> Arrays.asList(a.getAge(), a.getBrith()))
                .flatMap(a -> a.stream())
                .distinct()
                .forEach(System.out::println);

        List<Object> list = peoples.stream().map(a -> Arrays.asList(a.getAge(), a.getBrith())).flatMap(a->a.stream()
        ).collect(Collectors.toList());
        System.out.println();
    }

    /**
     * 截断流limit,跳过元素skip
     ***/
    @Test
    public void testLimitAndSkip() {
        peoples.stream()
                .filter(a -> {
                    System.out.println("过滤");
                    return a.getId().equals("kevin");
                })
                .skip(3)//跳过流中的前三个元素
                .limit(2)//只获取流中的两个元素
                .forEach(System.out::println);
    }

    /**
     * 排序 sorted
     ***/
    @Test
    public void sortedTest() {
        //自然排序
        list.forEach(System.out::print);
        List list1 = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println();
        list1.forEach(System.out::print);
        System.out.println();
        //定制排序  此规则从小到大
        peoples = peoples.stream()
                .sorted((a, b) -> {
                    if (a.getAge() > b.getAge()) {
                        return 1;
                    } else if (a.getAge() < b.getAge()) {
                        return -1;
                    } else {
                        return 0;
                    }
                })
                .collect(Collectors.toList());
        peoples.stream().map(People::getAge).forEach(System.out::println);

        //逆序排列
        peoples.stream().sorted(Comparator.comparing(People::getAge).reversed()).map(People::getAge).forEach(System.out::println);
    }

    /**
     * 查找与匹配 allMatch与anyMatch
     ***/
    @Test
    public void matchTest() {
        //匹配所有
        boolean allMatch = peoples.stream().allMatch(a -> a.getId().equals("kevin"));
        //至少有一个匹配
        boolean anyMatch = peoples.stream().anyMatch(a -> a.getId().equals("kevin"));
        //完全没有匹配
        boolean noneMatch = peoples.stream().noneMatch(a -> a.getId().equals("kevin"));

        //返回第一个元素
        Optional first = peoples.stream().findFirst();
        System.out.println(first.get());
        //返回任意一个元素
        Optional any = peoples.stream().findAny();
        //返回流中元素个数
        Long count = peoples.stream().count();
        //返回流中的最大值
        Optional max = peoples.stream().max((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        Optional max1 = peoples.stream().max(Comparator.comparing(People::getAge));
        System.out.println(max.get());
        //返回流中最小的值
        Optional min = peoples.stream().min((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        Optional min1 = peoples.stream().min(Comparator.comparing(People::getAge));
    }

    /**
     * reduce  归约 : 可以将流中元素反复结合在一起，得到一个值
     ***/
    @Test
    public void testReduce() {
        //1 reduce（T identitty，BinaryOperator）首先，需要传一个起始值，然后，传入的是一个二元运算
        // identitty 起始值,然后与集合中的值进行相应的运算，再次赋值给 identity 然后在进行运算
        int sum = list.stream().reduce(100, (a, b) -> a + b);
        System.out.println(sum);

        // 2 reduce（BinaryOperator）此方法相对于上面方法来说，没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中。
        //map和reduce的连接通常称为map-reduce模式，因Google用它来进行网络搜索而出名。用map 来提取 对象中某个属性，然后再用reduce 进行归约
        Optional<Integer> optional = peoples.stream().map(a -> a.getAge()).reduce(Integer::sum);
        Optional<Integer> optiona = peoples.stream().map(a -> a.getAge()).reduce((a, b) -> a + b);
        System.out.println(optional.get());
    }

    /**
     * collect 收集collect（将流转换为其他形式。接收一个Collector接口得实现，用于给其他Stream中元素做汇总的方法） **
     */
    @Test
    public void testCollect() {
        // Collector接口中方法得实现决定了如何对流执行收集操作（如收集到List，Set，Map）。
        // 但是Collectors实用类提供了很多静态方法，可以方便地创建常见得收集器实例。
//      peoples.stream().collect(Collectors.toList());
//      peoples.stream().collect(Collectors.toSet());
//      //指定set类型
//      peoples.stream().collect(Collectors.toCollection(HashSet::new));
//      peoples.stream().collect(Collectors.toMap(People::getId,People::getAge));
//      //转map
//      peoples.stream().collect(Collectors.toMap(People::getId,People::getAge));
//      //计算元素个数
//      long cout=peoples.stream().map(People::getAge).collect(Collectors.counting());
//
//      //元素汇总
//      DoubleSummaryStatistics sum=peoples.stream().collect(Collectors.summarizingDouble(People::getAge));
//      IntSummaryStatistics sumInt=peoples.stream().collect(Collectors.summarizingInt(People::getAge));
//
//      //joining 拼接字符串
//      String name=peoples.stream().map(People::getName).collect(Collectors.joining(",","头","尾"));
//      //元素之间使用逗号拼接
//      String name1=peoples.stream().map(a->a.getName()).collect(Collectors.joining(","));
//      //元素直接拼接在一起
//      String name2=peoples.stream().map(People::getName).collect(Collectors.joining());
//
//      //求平均值
//      Double db=peoples.stream().collect(Collectors.averagingInt(People::getAge));
//      Double db1=peoples.stream().collect(Collectors.averagingLong(People::getAge));
//      Double db2=peoples.stream().collect(Collectors.averagingDouble(People::getAge));
//
//      //求最大最小值
//      Optional<People> people=peoples.stream().collect(Collectors.maxBy((a,b)->Integer.compare(a.getAge(),b.getAge())));
//      Optional<People> people1=peoples.stream().collect(Collectors.maxBy(Comparator.comparing(People::getAge)));
//
//      Optional<People> people2=peoples.stream().collect(Collectors.minBy((a,b)->Integer.compare(a.getAge(),b.getAge())));
//
//      //groupingBy 分组
//      Map<String,List<People>> map=peoples.stream().collect(Collectors.groupingBy(People::getId));
        // 分组内的数量
        Map<Integer, Long> map = peoples.stream().collect(Collectors.groupingBy(People::getAge, Collectors.counting()));
//      //多级分组
//      Map<String, Map<Integer, List<People>>> collect =peoples.stream().collect(Collectors.groupingBy(People::getId,Collectors.groupingBy(People::getAge)));
//      Map<String, Map<String, List<People>>> collect1 =peoples.stream().collect(Collectors.groupingBy(People::getId,Collectors.groupingBy(
//              e-> {
//                if(e.getAge()>10){
//                    return "大人";
//      }else{
//                    return "小孩";
//                }
//              }
//
//      )));
//      //partitioningBy,分区，根据条件将表分为不同区块
//      Map<Boolean, List<People>> collect4 =peoples.stream().collect(Collectors.partitioningBy(a->a.getAge()>10));
//      Map<Boolean, List<People>> collect3 = peoples.stream().collect(Collectors.groupingBy(e -> e.getAge() > 30));
        List<People> list = peoples.stream().sorted((a, b) -> {
            if (a.getAge() > b.getAge()) {
                return -1;
            } else if (a.getAge() < b.getAge()) {
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
        Map<String, List<People>> map1 = list.stream().collect(Collectors.groupingBy(People::getId));
        Map<String, Set<Integer>> map2 = list.stream().collect(Collectors.groupingBy(People::getId, Collectors.mapping(People::getAge, Collectors.toSet())));
        System.out.println();

    }
}
