package com.kevin.firstUtil;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;

public class StringBuilderUtil {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("kevin");


        //append
        System.out.println(sb.append(" is a good man"));
        // 单纯的拼在字符串后面一个数字
        System.out.println(sb.append(97));
        System.out.println(sb.append('a'));
        System.out.println(sb.append(true));
        System.out.println(sb.append(new char[1]));

        //拼接的是ascii码所代表的字符
        System.out.println(sb.appendCodePoint(10000));

        //delete 删除所给范围内的字符
        System.out.println(sb.delete(5,sb.length()));

        // deleteCharAt 删除第几位上的字符,因为使用的char数组，所以下标从0开始
        System.out.println(sb.deleteCharAt(4));

        //replace
        System.out.println(sb.replace(0,4,"zhao"));

        //insert
        char[]chars = new char[5];
        for (int i=0;i< chars.length;i++) {
            chars[i]='a';
        }
        System.out.println(sb.insert(4,97));
        System.out.println(sb.insert(4,"a"));
        System.out.println(sb.insert(4,chars));
        System.out.println(sb.insert(4,chars,2,2));
        System.out.println(sb.insert(4,true));
        System.out.println(sb.insert(4,"kevin"));
        //insert(int dstOffset, CharSequence s)此方法可以兼容其他的CharSequence实现类的对象

        //indexOf ,lastIndexOf使用string类的方法

        //  将字符串反转
        System.out.println(sb.reverse());

    }
}
