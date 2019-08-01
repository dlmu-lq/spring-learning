package top.itlq.spring.exampleBeans.javaConfig;

public class IceCream {

    private Chocolate chocolate;

    public Chocolate getChocolate() {
        return chocolate;
    }

    public void setChocolate(Chocolate chocolate) {
        this.chocolate = chocolate;
    }

    public IceCream(Chocolate chocolate){
        this.chocolate = chocolate;
    }

    public void init(){
        System.out.println("iceCream method init");
    }
}
