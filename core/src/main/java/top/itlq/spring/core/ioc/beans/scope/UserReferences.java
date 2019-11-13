package top.itlq.spring.core.ioc.beans.scope;

public class UserReferences implements References{
    private static int count;
    private int id = count++;

    @Override
    public void introduce(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "UserReferences{" +
                "id=" + id +
                '}';
    }
}