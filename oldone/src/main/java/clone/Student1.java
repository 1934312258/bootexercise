package clone;

//深度克隆
/*
 * 在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，
 * 深克隆将原型对象的所有引用对象也复制一份给克隆对象。
简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制。
 */
class Address implements Cloneable {
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        // TODO Auto-generated method stub
        Address add = null;
        try {
            add = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return add;
    }

}

class Studen implements Cloneable {
    private int num;
    private Address add;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        // TODO Auto-generated method stub
        Studen stu = null;
        try {
            stu = (Studen) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//浅克隆
        stu.add = (Address) add.clone();//深度克隆
        return stu;
    }

}

public class Student1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address add = new Address();
        add.setAdd("china");
        Studen stu = new Studen();
        stu.setAdd(add);
        stu.setNum(123);
        Studen stu1 = (Studen) stu.clone();
        System.out.println("stu " + stu.getNum() + stu.getAdd().getAdd());
        System.out.println("stu1 " + stu1.getNum() + stu1.getAdd().getAdd());
        add.setAdd("american");
        //stu1.getAdd().setAdd("Australia");
        System.out.println("stu " + stu.getNum() + stu.getAdd().getAdd());
        System.out.println("stu1 " + stu1.getNum() + stu1.getAdd().getAdd());

    }
}
