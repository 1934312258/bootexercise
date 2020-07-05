package clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//序列化实现深度克隆
class Inner implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1947384527406356609L;
	/**
	 * 
	 */
	
	public String name="";
	public Inner(String name){
		this.name=name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name 值为："+name;
	}
	
}

public class Student3 implements Serializable{
	/**
	 * 深度克隆需要对象及其对象的所有对象属性都实现序列化
	 */
	private static final long serialVersionUID = 4473430972321616880L;
	public Inner inner;
	public Student3 myclone() throws IOException, ClassNotFoundException{
		Student3 stu=null;
		//将该对象序列化成流，流中只是对象的拷贝，原对象仍然存在于jvm中，故可以实现对象的深度拷贝
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(baos);
		oos.writeObject(this);
		if(oos!=null){
			oos.close();
		}
		//将流序列化成对象
		ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois=new ObjectInputStream(bais);
		stu= (Student3) ois.readObject();
		if(ois!=null){
			ois.close();
		}
		
		return stu;
	}
}
