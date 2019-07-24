package top.itlq.spring.exampleBeans.scope;

public class UserManager {
    private final UserReferences userReferences;
    public UserManager(UserReferences userReferences){
        this.userReferences = userReferences;
    }
    public void printUser(){
        this.userReferences.introduce();
    }
}
