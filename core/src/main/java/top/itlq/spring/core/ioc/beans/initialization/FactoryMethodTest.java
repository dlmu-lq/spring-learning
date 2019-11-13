package top.itlq.spring.core.ioc.beans.initialization;

/**
 * 通过xml配置构造器/工厂方法实例化bean
 */
public class FactoryMethodTest {
    private FactoryMethodTest(){}

    private FactoryMethodTest(FactoryDependency factoryDependency,int i,String s){
        System.out.println("FactoryMethodTest构造器:" + "factoryDependency:" + factoryDependency + ",i:" + i + ",s:" + s);
    }

    /**
     * 静态工厂方法
     * @param i
     * @return
     */
    public static FactoryMethodTest createInstance(int i){
        System.out.println("FactoryMethodTest的静态工厂方法:" + "i:" + i);
        return new FactoryMethodTest();
    }

    /**
     * 非静态工厂方法，创建其他类型bean
     * @return
     */
    public FactoryDependency createOtherInstance(){
        System.out.println("FactoryMethodTest的非静态工厂方法");
        return new FactoryDependency();
    }
}
