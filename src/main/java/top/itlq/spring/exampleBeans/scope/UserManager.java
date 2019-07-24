package top.itlq.spring.exampleBeans.scope;

public class UserManager {
    private final References references;
    public UserManager(References references){
        this.references = references;
    }
    public void printUser(){
        this.references.introduce();
    }
}
