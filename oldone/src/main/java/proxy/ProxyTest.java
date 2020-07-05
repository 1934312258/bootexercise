package proxy;

//静态代理类
public class ProxyTest implements Person {
	private Person o;
	public  ProxyTest(Person o) {
		this.o=o;
	}
	@Override
	public void sayhello(String context, int age) {
		// TODO Auto-generated method stub
		System.out.println("ProxyTest sayhello begin");
		o.sayhello(context, age);
		System.out.println("ProxyTest sayhello end");
	}

	@Override
	public void sayGoodby(Boolean see, Double time) {
		// TODO Auto-generated method stub
		System.out.println("ProxyTest sayGoodby begin");
		o.sayGoodby(see, time);
		System.out.println("ProxyTest sayGoodby end");
	}
	public static void main(String[] args) {
	////student就是u被代理的对象，某些情况下，我们不希望修改已有代码，我们采用代理去间接的访问
		Student s=new Student();
		ProxyTest pt=new ProxyTest(s);
		pt.sayGoodby(true, 1.23);
		pt.sayhello("kdvfoi", 23);
	}
}
