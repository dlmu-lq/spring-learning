package top.itlq.spring.core.ioc.beans.scope;

public class UserManager {
    private final References references;
    public UserManager(References references){
        this.references = references;
    }
    public void printUser(){
        this.references.introduce();
    }
}
