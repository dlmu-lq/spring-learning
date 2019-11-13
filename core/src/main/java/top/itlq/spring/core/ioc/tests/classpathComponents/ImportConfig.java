package top.itlq.spring.core.ioc.tests.classpathComponents;

import org.springframework.context.annotation.Import;
import top.itlq.spring.core.ioc.beans.classpathComponents.base.Drink;

@Import(Drink.class)
public class ImportConfig {
}
