package ch.shukalovi.validator;

import ch.shukalovi.model.Order;

public class OrderValidatorExtended implements OrderValidator {
    @Override
    public boolean isOrderValid(Order order) {
        return true;
    }
}
