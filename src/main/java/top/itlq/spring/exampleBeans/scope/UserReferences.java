package top.itlq.spring.exampleBeans.scope;

public class UserReferences {
    private static int count;
    private int id = count++;

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
