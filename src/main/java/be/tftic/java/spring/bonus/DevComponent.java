package be.tftic.java.spring.bonus;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile("dev")
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DevComponent {

    @AliasFor(
            annotation = Component.class
    )
    String value() default "";



}
