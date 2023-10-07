package ch.shukalovi.validator;

import ch.shukalovi.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;

import javax.inject.Provider;

@Slf4j
public class OrderValidator {
    private int minOrderNo;

    private final Provider<NonThreadSafeOrderValidator> ntsov;

    public OrderValidator(Provider<NonThreadSafeOrderValidator> ntsov) {
        this.ntsov = ntsov;
    }

    public boolean isOrderNoValid(Order order) {
        boolean isValid = order.orderNo() > minOrderNo;
        log.info("{} is {}", order, isValid ? "valid" : "invalid");
        return isValid && ntsov.get().isValid();
    }

    public void setMinOrderNo(int minOrderNo) {
        this.minOrderNo = minOrderNo;
    }
}
