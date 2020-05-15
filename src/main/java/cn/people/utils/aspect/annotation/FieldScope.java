package cn.people.utils.aspect.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldScope {

    public String tableAlias() default "";

}
