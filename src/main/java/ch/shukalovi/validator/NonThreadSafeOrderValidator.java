package ch.shukalovi.validator;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NonThreadSafeOrderValidator {

    private final AtomicInteger invokedCount = new AtomicInteger();

    public boolean isValid() {
        invokedCount.incrementAndGet();
        log.info("NonThreadSafeOrderValidator invoked {} times", invokedCount.get());
        return true;
    }
}
