package ch.shukalovi.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * No destroy callback for prototype scope: Spring doesn't collect beans except 'singleton' scopes
 */
@Slf4j
@Component
@Scope(SCOPE_PROTOTYPE)
public class NonThreadSafeOrderValidator {

    private final AtomicInteger invokedCount = new AtomicInteger();

    public NonThreadSafeOrderValidator() {
        log.info("NonThreadSafeOrderValidator created");
    }

    public boolean isValid() {
        invokedCount.incrementAndGet();

        if (invokedCount.get() == 2) {
            throw new IllegalStateException("NonThreadSafeOrderValidator called twice!");
        }

        log.info("NonThreadSafeOrderValidator invoked {} times", invokedCount.get());
        return true;
    }
}
