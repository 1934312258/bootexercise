package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class guibing {
    // 归并排序的思路是二分排序，将数组拆分到不能再次拆分，然后主次归并，时间复杂度nlogn
    //二分使用递归思想进行二分，然后递归
    public static void main(String[] args) {
        int[]ints = {1,7,5,8,9,4,2};
        fenGe(ints,0,ints.length-1);
        System.out.println(Arrays.toString(ints));
    }

    public static void fenGe(int[] ints,int left,int right){
        if(left < right){
            int mid = (left + right)/2;
            fenGe(ints,left,mid);
            fenGe(ints,mid+1,right);
            heBing(ints,left,right,mid);
        }
    }

    public static void heBing(int[]ints,int left,int right,int mid){

        // 左侧数组第一个元素
        int l = left;
        // 右侧数组第一个元素
        int r = mid + 1;
        // 暂存数组存放数据下标
        int local = left;
        //暂存排序后的数据
        int [] temp = new int[ints.length];
        while(l <= mid && r <= right) {
            if (ints[l] < ints[r]) {
                temp[local] = ints[l];
                l++;
            } else {
                temp[local] = ints[r];
                r++;
            }
            local++;
        }
        // 如果左侧数组没有比较完
        while(l <= mid){
            temp[local++] = ints[l++];
        }

        // 如果右侧没有比较完
        while(r <= right){
            temp[local++] = ints[r++];
        }

        // 将排序后的数据赋值给原数组
        for(int i = left; i <= right; i++){
            ints[i] = temp[i];
        }
    }

}

