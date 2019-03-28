package top.itlq.spring.tests.classpathComponents;

import org.springframework.context.annotation.Import;
import top.itlq.spring.exampleBeans.classpathComponents.base.Drink;

@Import(Drink.class)
public class ImportConfig {
}
