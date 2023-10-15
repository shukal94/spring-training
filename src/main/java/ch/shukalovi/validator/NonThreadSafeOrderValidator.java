package ch.shukalovi.validator;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * No destroy callback for prototype scope: Spring doesn't collect beans except 'singleton' scopes
 */
@Slf4j
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
