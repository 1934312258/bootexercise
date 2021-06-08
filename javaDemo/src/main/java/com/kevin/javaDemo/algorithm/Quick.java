package com.kevin.javaDemo.algorithm;

import java.util.Arrays;

public class Quick {

    // 快速排序的思路是，选取一个基准数，然后从后面与基准数比较，如果小于基准数则换位并记录右侧已比较的位置，
    // 然后基准数从前向后比较，如果大于基准数则进行换位，然后继续向后比较，直到比较完成为止，
    // 此时基准数左边数据一定小于基准数，右边数据一定大于基准数，然后以基准数为界递归进行拆分
    public static void main(String[] args) {
        int[]ints = {1,7,5,8,9,4,2};
        quick(ints,0,ints.length-1);
        System.out.println(Arrays.toString(ints));

    }
    public static void quick(int[] ints,int ll,int rr){
        int left = ll;
        int right = rr;
        int index = ints[left];
        while(left < right) {
            while (left < right && index <= ints[right]) {
                right--;
            }
            if(left < right) {
                ints[left] = ints[right];
                ints[right] = index;
                left++;
            }
            while(left < right && index >= ints[left]){
                left++;
            }
            if(left < right){
               ints[right] = ints[left];
               ints[left] = index;
               right--;
            }
        }
        if(ll < left){
            quick(ints,ll,left-1);
        }
        if(right < rr){
            quick(ints,left+1,rr);
        }
    }

}
