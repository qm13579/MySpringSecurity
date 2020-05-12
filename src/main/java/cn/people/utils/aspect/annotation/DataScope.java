package cn.people.utils.aspect.annotation;

import java.lang.annotation.*;

/**
 * 数据权限注解
 * @author apple
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    public String deptAlias() default "";

    public String userAlias() default "";
}
