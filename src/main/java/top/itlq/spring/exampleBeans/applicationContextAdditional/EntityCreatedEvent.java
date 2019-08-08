package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EntityCreatedEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {
    /**
     * Create a new ApplicationEvent.
     *
     * @param entity the object on which the event initially occurred (never {@code null})
     */
    public EntityCreatedEvent(T entity) {
        super(entity);
    }

    /**
     * 必须加，不然由于类型擦除，EventListener不只能接收具体泛型参数的事件
     * @return
     */
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),ResolvableType.forInstance(getSource()));
    }
}
