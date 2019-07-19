package com.zhaojun.annotation;

import java.lang.annotation.*;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:23
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomRequestMapping {

    String value() default "";
}
