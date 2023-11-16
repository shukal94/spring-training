package ch.shukalovi.subscriber;

import ch.shukalovi.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryService {
    @EventListener //will be called after OrderService::createOrder in one thread e.g. synchronous
    public void onOrderCreated(OrderCreatedEvent event) {
        log.info("Order created: {}", event.getOrderNumber());
    }
}
