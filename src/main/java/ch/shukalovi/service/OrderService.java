package ch.shukalovi.service;

import ch.shukalovi.annotations.Trace;
import ch.shukalovi.exceptions.OrderCreationException;
import ch.shukalovi.model.Order;
import ch.shukalovi.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderService {
    private final List<OrderValidator> validators;

    public OrderService(List<OrderValidator> validators) {
        this.validators = validators;
        log.info("Order Service created.");

    }

    private void init() {
        log.info("Order Service init.");
    }

    private void destroy() {
        log.info("Order Service destroy.");
    }

    public Order createOrder(Order order) {
        if (validators.stream().allMatch(ov -> ov.isOrderValid(order))) {
            log.info("Order {} was created.", order);
            return order;
        }

        throw new OrderCreationException(String.format("Order %s is not valid!", order));
    }
}
