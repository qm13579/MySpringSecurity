package cn.people.utils.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义导出excel数据注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    /**导出的excel中的名字 */
    String name() default "";

    /**日期格式 如yyyy-MM-dd*/
    String dateFormat() default "";

    /**读取内容转表达式(如：0=男，1=女，2=未知)*/
    String readConverterExp() default "";

    /**实体类识别*/
    String key() default "";
}
