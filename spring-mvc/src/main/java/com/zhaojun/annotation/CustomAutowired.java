package com.zhaojun.annotation;

import java.lang.annotation.*;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:27
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAutowired {

    String value() default "";
}
