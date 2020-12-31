package equalsAndhashcode;

import java.util.HashSet;
import java.util.Set;

public class EqualHash {
    private int id;
    private String firstname;
    private String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        EqualHash e = (EqualHash) o;
        return (this.getId() == e.getId());
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }

    public static void main(String[] args) {
        //上面的程序将输出false，但是，事实上上面两个对象代表的是通过一个employee。真正的商业逻辑希望我们返回true。
        //为了达到这个目的，我们需要重写equals方法
        EqualHash eh1 = new EqualHash();
        EqualHash eh2 = new EqualHash();
        Set<EqualHash> set = new HashSet<>();
        eh1.setId(100);
        eh2.setId(100);
        set.add(eh2);
        set.add(eh1);
        System.out.println(eh1.equals(eh2));
        System.out.println(set);
    }
}
