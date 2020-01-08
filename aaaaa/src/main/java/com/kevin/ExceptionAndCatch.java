package com.kevin;

/**
 * @author kevin
 * @date 2019-11-26 10:45
 * @description todo
 */
public class ExceptionAndCatch {
  public static void main(String[] args) throws Exception {
    int i = 1;
    boolean flag = true;
    while (flag) {
      try {
        System.out.println(i);
        i++;
        if (i == 10) {
          throw new Exception("数据太大了");
        }
      } catch (Exception e) {
        System.out.println("出错了");
      }
    }
  }
}