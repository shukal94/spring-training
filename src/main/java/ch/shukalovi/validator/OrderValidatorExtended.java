package ch.shukalovi.validator;

import ch.shukalovi.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderValidatorExtended implements OrderValidator {

    @Autowired
    public OrderValidatorExtended() {

    }

    @Override
    public boolean isOrderValid(Order order) {
        return true;
    }
}
