package ch.shukalovi.validator;

import ch.shukalovi.annotation.Trace;
import ch.shukalovi.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Provider;

@Slf4j
@Trace
public class OrderValidatorBasic implements OrderValidator {
    private int minOrderNo;

    private final Provider<NonThreadSafeOrderValidator> ntsov;

    @Autowired
    public OrderValidatorBasic(Provider<NonThreadSafeOrderValidator> ntsov) {
        this.ntsov = ntsov;
    }

    @Override
    public boolean isOrderValid(Order order) {
        boolean isValid = order.orderNo() > minOrderNo;
        log.info("{} is {}", order, isValid ? "valid" : "invalid");
        return isValid && ntsov.get().isValid();
    }

    public void setMinOrderNo(int minOrderNo) {
        this.minOrderNo = minOrderNo;
    }
}
