package Cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasDemo2 {
	private static AtomicStampedReference stamp=new AtomicStampedReference(100, 1);
	public static void main(String[] args) {
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(stamp.compareAndSet(100, 120,1,2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(stamp.compareAndSet(120, 100,2,3));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(stamp.compareAndSet(100, 130,1,2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
}
