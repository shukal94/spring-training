package ch.shukalovi.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * Changes the default scope (singleton) to prototype on beans definition level
 * We cannot inject any bean, beans are being created later, like we can use non-static objects in static methods.
 * Again, spring wasn't started here
 */
@Component
public class PrototypeByDefaultBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Arrays.stream(configurableListableBeanFactory.getBeanDefinitionNames()).forEach( name -> {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
            if (beanDefinition.getScope() == null || beanDefinition.getScope().trim().isEmpty() || Objects.equals(beanDefinition.getScope(), BeanDefinition.SCOPE_SINGLETON)) {
                beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
            }
        });
    }
}
