package cn.people.utils.aspect.annotation;

import cn.people.utils.common.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**资源名称*/
    String name() default "";

    /**资源key*/
    String key() default "";

    /**key prefix*/
    String prefix() default "";

    /**时间 单位秒*/
    int period() ;

    /**限制访问次数*/
    int count();

    /**限制类型*/
    LimitType limitType() default LimitType.CUSTOMER;

}
