package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class Charu {

    // 插入排序是希尔排序与归并排序的基础
    // 其思想是将数组 从头开始将第一位数之后的数据与第一位比较，两者排序，然后第三位与前两者排序，直至排序完成

    public static void main(String[] args) {
        int[]ints = {6,7,5,8,9,4,1};
        for(int i =1;i < ints.length;i++){
            for(int j = i -1;j >=0;j--){
                if(ints[j+1] < ints[j]){
                    ints[j+1] = ints[j+1]+ints[j];
                    ints[j] = ints[j+1] - ints[j];
                    ints[j+1] = ints[j+1] - ints[j];
                }else {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(ints));
    }
}
