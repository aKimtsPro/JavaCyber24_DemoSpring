package be.tftic.java.spring.bonus;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Profile("default")
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultComponent {

    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
