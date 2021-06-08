package com.kevin.firstUtil;

import java.sql.SQLOutput;

public class StringUtil {
    public static void main(String[] args) {


        //codePointAt 返回char对应的ascii的值

        //codePointBefore 返回前一个char对应的ascii的值

        // codePointCount 判断字符长度，用于uniCode字符，
        // 例如比如整数集合的数学符号"Z"（没办法打出来），它的代码点是U+1D56B，但它的代理单元是U+D835和U+DD6B，
        // 如果令字符串str = "/u1D56B"，机器识别的不是"Z"，而是一个代码点”/u1D56“和字符”B“
        String str = "/u1D56B";
        //offsetByCodePoints
        System.out.println(str.length());
        System.out.println(str.codePointCount(0, 7));
        System.out.println(str.offsetByCodePoints(0, 7));

        //compareTo,如果短字符串与长字符串的前几位相等，则返回两个字符串的长度差，否则返回第一个两个字符串不相等位的字符的码表值差
        String a = "a";
        String b = "b";
        System.out.println(a.compareTo(b));

        /// compareToIgnoreCase  通过内部类忽略大小写的比较两个字符串

        //regionMatches 当前字符串从toffset位开始与目标字符串从ooffset位开始比较len个字符，如果相等返回true否则false
        a = "abc";
        b = "abcd";
        System.out.println(b.regionMatches(0, a, 0, 3));
        // 重载的忽略大小写
        System.out.println(b.regionMatches(true, 0, a, 0, 3));

        // startsWith
        System.out.println(b.startsWith("abc"));
        System.out.println(b.startsWith("cd", 2));
        //endWith

        //indexOf 当参数是int值时代表char的ascii值
        System.out.println(b.indexOf(98));
        System.out.println(b.indexOf("b"));

        //lastIndexOf

        //substring
        System.out.println(b.substring(0));
        System.out.println(b.substring(0, 2));
        CharSequence subSequence = b.subSequence(0, 2);
        ///subSequence()同substring，返回类型为string的接口类型

        //concat 将两个字符串合并为一个
        System.out.println(a.concat(b));

        //replace 替换所有的
        String replace = "aaaaaaaaa";
        System.out.println(replace.replace('a', 'c'));
        System.out.println(replace.replace("a", "b"));
        System.out.println(replace.replaceAll("a", "b"));

        // replaceAll,replaceFirst可以使用正则匹配
        System.out.println(replace.replaceAll("(.*)runoob(.*)", "b"));
        System.out.println(replace.replaceFirst("(.*)runoob(.*)", "b"));

        // matches,字符串是否匹配正则表达式
        System.out.println(replace.matches("(.*)runoob(.*)"));

        // contains，是否包含，使用的indexOf方法
        System.out.println(b.contains("b"));

        //split,limit表示限制分割到第几位，0表示不限制，可以不适用此参数
        String split = "adaddaddadadd";
        String[] strs = split.split("a", 2);
        String[] strs1 = split.split("a", 0);
        //join 将多个字符串拼接为一个
        System.out.println(String.join("ad", "s", "d", "sd", "df"));

        //toLowerCase,toUpperCase 转换大小写
        System.out.println("ADFTYGRGFJHY".toLowerCase());
        System.out.println(replace.toUpperCase());

        //trim 去掉开头与结尾的空格
        System.out.println(" dfdfF  ".trim().length());

        //toCharArray 将字符串的char数组拷贝一份出来

        //format  此处会重点总结


        //valueOf 获取一个新的字符串,重载方法较多
        String.valueOf("fdsg");
        String.valueOf('a');
        String.valueOf(true);
        String.valueOf(12);
        String.valueOf(new char[10]);
        String.valueOf(new char[10], 2, 6);
        String.valueOf(12L);

        // intern 字符串调用此方法时会检查常量池中有无此字符串，如果有则返回该字符串的引用（对于new的字符串有实际意义），如果没有则在常量池中添加此字符串并返回引用（适用于多个字符串拼接）
        //当比较时如果是拼接字符串，则所有的拼接变量不能有变量，否则不会进入常量池
        String intern = new String("intern");
        String s = "intern";
        String d = "inter";
        String f = "inter" + "n";
        String g = "n";
        System.out.println(intern == s);
        System.out.println(intern.intern() == s);
        System.out.println(s.intern() == f);
        System.out.println(intern.intern() == d + "n");

        //equals 比较值是否相等 == 比较值与内存地址
        String equal = new String("equal");
        String equals = new String("equal");
        System.out.println(equal.equals(equals));
        // equalsIgnoreCase  比较值是否相等,忽略大小写
        equal.equalsIgnoreCase(equals);
    }
}