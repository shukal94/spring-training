package ch.shukalovi.validator;

import ch.shukalovi.model.Order;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("reserve")
public class OrderValidatorExtended implements OrderValidator {

    @Override
    public boolean isOrderValid(Order order) {
        return true;
    }
}
