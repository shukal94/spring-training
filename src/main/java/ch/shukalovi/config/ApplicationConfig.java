package ch.shukalovi.config;

import ch.shukalovi.service.OrderService;
import ch.shukalovi.validator.OrderValidator;
import ch.shukalovi.validator.OrderValidatorBasic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/***
 * Java-based config, allows us to create various beans for one bean definition
 * 1 definition - N beans
 * external classes as a bean
 * move all the bean-related annotations here!!
 */
@Configuration
@PropertySource("app.properties")
public class ApplicationConfig {

    @Bean
    public OrderService orderService() {
        // basicValidator is still singleton, while startup ApplicationConfig delegates
        // the logic to its proxy (CGLIB Proxy, ApplicationConfig's subclass)
        // requests scope, and if there's no bean, creates it and returns (for singleton and @Component annotated only)
        // CGLIBProxy::basicValidator() called, not ApplicationConfig::basicValidator()
        // for prototype scope 4 validators created
        basicValidator();
        basicValidator();
        basicValidator();
        return new OrderService(basicValidator());
    }

    /**
     * OrderValidatorBasic should NOT be annotated as @Component
     */
    @Bean
    @Primary
    public OrderValidator basicValidator() {
        System.out.println("basicValidator is called.");
        return new OrderValidatorBasic();
    }

    @Bean
    public OrderValidator basicValidatorWithOverridenMinOrderNo() {
        OrderValidatorBasic ovb = new OrderValidatorBasic();
        ovb.setMinOrderNo(100);
        return ovb;
    }
}
