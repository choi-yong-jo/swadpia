package kr.co.swadpia.config.annotation.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoLogMethodAop {
    boolean leadTime() default true;
    boolean param() default false;
    boolean result() default false;
}
