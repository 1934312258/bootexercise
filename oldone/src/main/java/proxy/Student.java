package proxy;

public class Student implements Person {

	@Override
	public void sayhello(String context, int age) {
		// TODO Auto-generated method stub
		System.out.println("hello"+context+" "+age);
	}

	@Override
	public void sayGoodby(Boolean see, Double time) {
		// TODO Auto-generated method stub
		System.out.println("goodby"+time+" "+see);
	}

}
