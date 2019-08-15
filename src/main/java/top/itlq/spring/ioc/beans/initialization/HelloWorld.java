package top.itlq.spring.ioc.beans.initialization;

/**
 * spring 通过xml创建类测试
 */
public class HelloWorld {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("HelloWorld调用setName()");
        this.name = name;
    }

    public HelloWorld(){
        System.out.println("HelloWorld构造器");
    }

    public void hello(){
        System.out.println("hello from: " + name);
    }
}
