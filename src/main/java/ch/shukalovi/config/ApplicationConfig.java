package ch.shukalovi.config;

import ch.shukalovi.service.OrderService;
import ch.shukalovi.validator.OrderValidator;
import ch.shukalovi.validator.OrderValidatorBasic;
import org.springframework.context.annotation.*;

/***
 * Java-based config, allows us to create various beans for one bean definition or make complex configurations
 * 1 definition - N beans
 * external classes as a bean
 * move all the bean-related annotations here!!
 * basicValidator is still singleton, while startup ApplicationConfig delegates
 * the logic to its proxy (CGLIB Proxy, ApplicationConfig's subclass)
 * requests scope, and if there's no bean, creates it and returns (for singleton and @Component annotated only)
 * CGLIBProxy::basicValidator() called, not ApplicationConfig::basicValidator()
 */
@Profile("!test")
@Configuration
@PropertySource("classpath:app.properties")
@PropertySource("classpath:app.properties")
@PropertySource("classpath:app.properties")
@PropertySource("classpath:app.properties")
public class ApplicationConfig {

    /**
     * OrderValidatorBasic should NOT be annotated as @Component
     */
    @Bean
    @Primary
    public OrderValidator basicValidator() {
        System.out.println("basicValidator is called.");
        return new OrderValidatorBasic();
    }
}
