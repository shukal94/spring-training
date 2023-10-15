package ch.shukalovi.service;

import ch.shukalovi.exceptions.OrderCreationException;
import ch.shukalovi.model.Order;
import ch.shukalovi.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderService {
    private final OrderValidator validator;

    public OrderService(OrderValidator validator) {
        this.validator = validator;
        log.info("Order Service created.");

    }

    public void init() {
        log.info("Order Service init.");
    }

    public void destroy() {
        log.info("Order Service destroy.");
    }

    public Order createOrder(Order order) {
        if (validator.isOrderNoValid(order)) {
            log.info("Order {} was created.", order);
            return order;
        }

        throw new OrderCreationException(String.format("Order %s is not valid!", order));
    }
}
