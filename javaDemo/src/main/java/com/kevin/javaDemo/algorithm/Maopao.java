package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class Maopao {
    public static void main(String[] args) {
        int [] ints = {1,2,3,4,5};
        for(int i =0; i< ints.length;i++){
            // 优化当所有数据已经是有序的，则不用继续比较，通过开关跳出循环
            boolean flag = false;
            for(int j =0; j< ints.length - i -1;j++){
                int temp = 0;
                if(ints[j] < ints[j+1]){
//                    ints[j] = temp;
//                    ints[j] = ints[j+1];
//                    ints[j+1] = temp;

                    // 优化减少变量的创建
                    ints[j] = ints[j] + ints[j+1];
                    ints[j+1] = ints[j] - ints[j+1];
                    ints[j] = ints[j] - ints[j+1];
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        System.out.println(Arrays.toString(ints));
    }
}
