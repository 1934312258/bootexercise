package clone;
//浅克隆
/*
 *在浅克隆中，如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，
 *则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。
简单来说，在浅克隆中，当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制。 
 * 
 * 
 */
public class Student implements Cloneable{
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Student stu=null;
		stu=(Student) super.clone();
		return stu;
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		Student stu=new Student();
		stu.setNum(12345);
		Student stu1=(Student) stu.clone();
		System.out.println("stu"+stu.getNum());
		System.out.println("stu1"+stu1.getNum());
		stu1.setNum(7890);
		System.out.println("stu"+stu.getNum());
		System.out.println("stu1"+stu1.getNum());
	}
}
