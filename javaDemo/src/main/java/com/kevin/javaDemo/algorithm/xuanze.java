package com.kevin.javaDemo.algorithm;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;

import java.util.Arrays;

public class xuanze {
    // 选择排序，冒泡排序与快速排序的基础
    //选择排序的思想是第一位与第一位之后的所有数中的最小值换位，之后第二位继续，直至倒数第二位

    public static void main(String[] args) {
        int[]ints = {1,7,5,8,9,4,2};
        for(int i = 0; i<ints.length-1;i++){
            int temp = ints[i];
            int s;
            int a = i;
            for(int j = i; j<(ints.length -1);j++){
              if(temp >ints[j+1]){
                  temp = ints[j+1];
                  a = j+1;
              }
            }
            if(a != i){
                ints[a] = ints[i];
                ints[i] = temp;
            }
        }
        System.out.println(Arrays.toString(ints));
    }
}
