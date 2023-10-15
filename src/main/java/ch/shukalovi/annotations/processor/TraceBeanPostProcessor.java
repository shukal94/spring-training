package ch.shukalovi.annotations.processor;

import ch.shukalovi.annotations.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class TraceBeanPostProcessor implements BeanPostProcessor, Ordered {
    Set<String> beansToProcess = new HashSet<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Trace.class)) {
            beansToProcess.add(beanName);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beansToProcess.contains(beanName)) {
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.printf("%s::%s with args %s called%n", beanName, method.getName(), Arrays.toString(args));
                return method.invoke(bean, args);
            });
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
