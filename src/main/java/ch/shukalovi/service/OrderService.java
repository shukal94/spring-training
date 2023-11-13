package ch.shukalovi.service;

import ch.shukalovi.exceptions.OrderCreationException;
import ch.shukalovi.model.Order;
import ch.shukalovi.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/***
 * JDK Proxy won't work, need at least one interface to be implemented
 * if we define init-method and destroy method in XML, we modify bean definition, bean will be created with those hooks
 * if we use @PostConstruct, @PreDestroy - we just create bean WITHOUT hooks and use standard bean postprocessor
 * after the bean creation
 * Lifecycle note: bean is being created first, annotations are bean post-processors, which are working after bean created
 * Autowiring for constructor params is not required!!!
 * @Controller - in Spring Web handles requests
 * @Component - bean definition
 * @Service - alias for @Component, used to mark business service, service = component
 * @Repository - equal to @Component, but has exception translation from different DBMS's, unifies DB exceptions to Spring exceptions
 * using annotation-based config we can create only ONE bean, @see ch.shukalovi.config.ApplicationConfig how to resolve 1 definition - N beans relation
 * also we cannot modify the external classes if we want to use those as beans,  @see ch.shukalovi.config.ApplicationConfig how to resolve it
 */
@Slf4j
@Service
//@Service - if we leave this annotation there will be 2 bean definitions - annotation and java - based
public class OrderService {
    private final OrderValidator validator;

    private String prop;

    @Value("classpath:file.txt")
    private Resource resource;

    public OrderService(OrderValidator validator) {
        this.validator = validator;
        log.info("Order Service created.");

    }

    @PostConstruct
    private void init() {
        log.info("Order Service init. {}", prop);
    }

    @PreDestroy
    private void destroy() {
        log.info("Order Service destroy.");
    }

    public Order createOrder(Order order) {
        if (validator.isOrderValid(order)) {
            log.info("Order {} was created.", order);
            return order;
        }

        throw new OrderCreationException(String.format("Order %s is not valid!", order));
    }

    @Value("${prop1:default}")
    public void setProp(String prop) {
        this.prop = prop;
    }

    public OrderValidator getValidator() {
        return validator;
    }

    public void readResource() {
        try (InputStream inputStream = resource.getInputStream()) {
            System.out.println(IOUtils.readLines(inputStream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
