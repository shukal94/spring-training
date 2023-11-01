package ch.shukalovi.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Qualifier has @Documented annotation, so we can use @Qualifier as an annotation for another annotation
 * in our case @Main is an alias for @Qualifier("main)
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("main")
public @interface Main {
}
