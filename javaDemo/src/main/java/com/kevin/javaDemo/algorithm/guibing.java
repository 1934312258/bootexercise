package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class guibing {
    // 归并排序的思路是二分排序，将数组拆分到不能再次拆分，然后主次归并，时间复杂度nlogn
    //二分使用递归思想进行二分，然后递归
    public static void main(String[] args) {
        int[]ints = {1,7,5,8,9,4,2};

        System.out.println(Arrays.toString(ints));
    }

    public void fenGe(int[] ints,int left,int right){
        int mid = ints.length/2;
        if(left < right){
            fenGe(ints,left,mid);
            fenGe(ints,mid+1,right);
            heBing(ints,left,right,mid);
        }
    }

    public void heBing(int[]ints,int left,int right,int mid){

    }

}
