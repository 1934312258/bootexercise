package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class xier {
    //希尔排序希尔排序其实是插入排序的一个改进版，不稳定。
    //希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，
    // 当增量减至1时，整个文件恰被分成一组，算法便终止。
    // 值交换时以增量值为跨度来交换加上增量后下标数组的值

    public static void main(String[] args) {
        int[]ints = {1,7,5,8,9,4,2};
        int n = ints.length;
        for(int add = n/2;add >=1;add/=2) {
            for (int i = add; i < n; i++) {
                int data = ints[i];
                int j = i - add;
                for (; j >= 0; j-=add) {
                    if (ints[j] > data) {
                        ints[j + add] = ints[j];
                    } else {
                        break;
                    }
                }
                // 此时j已经减过add了，j在加add正好是交换过数据的数组下标，正好将用于交换的data值重新赋值给数组
                ints[j + add] = data;
            }
        }
        System.out.println(Arrays.toString(ints));
    }
}
