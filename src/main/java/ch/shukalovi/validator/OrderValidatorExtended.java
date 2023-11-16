package ch.shukalovi.validator;

import ch.shukalovi.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorExtended implements OrderValidator {

    @Override
    public boolean isOrderValid(Order order) {
        return true;
    }

    @Override
    public void testBPP() {

    }
}
