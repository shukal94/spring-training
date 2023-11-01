package ch.shukalovi.validator;

import ch.shukalovi.annotation.Main;
import ch.shukalovi.annotation.Trace;
import ch.shukalovi.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Provider;

@Slf4j
@Trace
//@Main
public class OrderValidatorBasic implements OrderValidator {
    private int minOrderNo;

    private final ObjectFactory<NonThreadSafeOrderValidator> ntsov;

    public OrderValidatorBasic(ObjectFactory<NonThreadSafeOrderValidator> ntsov) {
        this.ntsov = ntsov;
    }

    @Override
    public boolean isOrderValid(Order order) {
        boolean isValid = order.orderNo() > minOrderNo;
        log.info("{} is {}", order, isValid ? "valid" : "invalid");
        return isValid && ntsov.getObject().isValid();
    }

    public void setMinOrderNo(int minOrderNo) {
        this.minOrderNo = minOrderNo;
    }
}
