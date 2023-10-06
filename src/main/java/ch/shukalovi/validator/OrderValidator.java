package ch.shukalovi.validator;

import ch.shukalovi.model.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderValidator {
    private int minOrderNo;

    public boolean isOrderNoValid(Order order) {
        boolean isValid = order.orderNo() > minOrderNo;
        log.info("{} is {}", order, isValid ? "valid" : "invalid");
        return isValid;
    }

    public void setMinOrderNo(int minOrderNo) {
        this.minOrderNo = minOrderNo;
    }
}
