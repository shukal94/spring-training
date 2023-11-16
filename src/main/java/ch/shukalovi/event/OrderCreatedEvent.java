package ch.shukalovi.event;

public class OrderCreatedEvent {
    private final Integer orderNumber;


    public OrderCreatedEvent(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }
}
