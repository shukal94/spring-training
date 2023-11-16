package ch.shukalovi.config;

import ch.shukalovi.service.OrderService;
import ch.shukalovi.validator.OrderValidator;
import ch.shukalovi.validator.OrderValidatorBasic;
import org.springframework.context.annotation.*;

import static java.lang.Integer.MIN_VALUE;

@Profile("test")
@Configuration
@PropertySource("app.properties")
public class TestApplicationConfig {

    /**
     * OrderValidatorBasic should NOT be annotated as @Component
     */
    @Bean
    @Primary
    public OrderValidator testValidator() {
        System.out.println("testValidator is called.");
        OrderValidatorBasic ovb = new OrderValidatorBasic();
        ovb.setMinOrderNo(MIN_VALUE);
        return new OrderValidatorBasic();
    }
}
