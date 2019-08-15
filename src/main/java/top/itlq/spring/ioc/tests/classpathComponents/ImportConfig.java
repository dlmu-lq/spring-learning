package top.itlq.spring.ioc.tests.classpathComponents;

import org.springframework.context.annotation.Import;
import top.itlq.spring.ioc.beans.classpathComponents.base.Drink;

@Import(Drink.class)
public class ImportConfig {
}
