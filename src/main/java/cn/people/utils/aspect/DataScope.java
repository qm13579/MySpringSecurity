package cn.people.utils.aspect;

import java.lang.annotation.*;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    public String deptAlias() default "";

    public String userAlias() default "";
}
