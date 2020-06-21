package top.itlq.spring.core.ioc.beans.scope;

import org.springframework.beans.factory.annotation.Lookup;

public class UserManager {
    private final References references;
    public UserManager(References references){
        this.references = references;
    }
    public void printUser(){
        this.references.introduce();
        this.references.introduce();
    }
    public void printUserM(){
        References references = referencesM();
        references.introduce();
        references.introduce();
    }

    @Lookup
    public References referencesM(){
        return null;
    };
}
