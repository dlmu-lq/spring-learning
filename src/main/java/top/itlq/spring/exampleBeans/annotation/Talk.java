package top.itlq.spring.exampleBeans.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;


public class    Talk {

    private Event event;

    @Autowired(required = false)
    public Talk(Person person){
        System.out.println("person injected");
    }

    @Autowired(required = false)
    public Talk(Music music){
        System.out.println("music injected");
    }

    @Autowired(required = false)
//    public Talk(Event event, Optional<Music> music){ // Optional可以用来不存在注入
    public Talk(Event event, @Nullable Music music){ // @Nullable可以用来不存在注入
//    public Talk(Event event, Music music){ // 用这个不会使用该构造器，用上面两个，虽然 Music不存在，但依然能注入null
        System.out.println("event & music injected");
        System.out.println("Talk event:" + event);
    }

    public Event getEvent() {
        return event;
    }

//    @Autowired
    public void setEvent(Event event) {
        this.event = event;
    }

}
