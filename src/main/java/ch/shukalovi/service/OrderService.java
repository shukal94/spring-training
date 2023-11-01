package ch.shukalovi.service;

import ch.shukalovi.annotation.Main;
import ch.shukalovi.exceptions.OrderCreationException;
import ch.shukalovi.model.Order;
import ch.shukalovi.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * JDK Proxy won't work, need at least one interface to be implemented
 * if we define init-method and destroy method in XML, we modify bean definition, bean will be created with those hooks
 * if we use @PostConstruct, @PreDestroy - we just create bean WITHOUT hooks and use standard bean postprocessor
 * after the bean creation
 * Lifecycle note: bean is being created first, annotations are bean post-processors, which are working after bean created
 * Autowiring for constructor params is not required!!!
 *
 */
@Slf4j
public class OrderService {
    private final OrderValidator validator;

    public OrderService(OrderValidator validator) {
        this.validator = validator;
        log.info("Order Service created.");

    }

    @PostConstruct
    private void init() {
        log.info("Order Service init.");
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
}
