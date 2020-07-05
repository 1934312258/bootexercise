package com.kevin;

/**
 * @author
 * @date 2020-5-27 16:56
 * @description todo
 **/
public class Test2 implements TestInterface {
    @Override
    public void test1() {
    System.out.println("多个怎么解决");
    }
    static boolean  first;
  public static void main(String[] args) {
      int i=1;
    do{
System.out.println(i);
i++;
    }while (i==5);
  }
}
