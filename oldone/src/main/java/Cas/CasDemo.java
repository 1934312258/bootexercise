package Cas;

import java.util.concurrent.atomic.AtomicInteger;


public class CasDemo {
	static AtomicInteger integer=new AtomicInteger(10);
	static int a=21;
	public static int increase1(){
		return a++;
	}
	public static Integer increase(){
		//原子类通过while死循环不断尝试cas操作，直到成功为止
		return integer.incrementAndGet();
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				System.out.println(increase1());
			}).start();
		}
	}
}
