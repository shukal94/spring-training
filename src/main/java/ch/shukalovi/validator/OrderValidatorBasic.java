package ch.shukalovi.validator;

import ch.shukalovi.annotation.Trace;
import ch.shukalovi.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Trace
public class OrderValidatorBasic implements OrderValidator {
    private int minOrderNo;

    @Override
    public boolean isOrderValid(Order order) {
        boolean isValid = order.orderNo() > minOrderNo;
        log.info("{} is {}", order, isValid ? "valid" : "invalid");
        return isValid;
    }

    @Override
    public void testBPP() {

    }

    @Value("${minOrderNo}")
    public void setMinOrderNo(int minOrderNo) {
        this.minOrderNo = minOrderNo;
    }
}
