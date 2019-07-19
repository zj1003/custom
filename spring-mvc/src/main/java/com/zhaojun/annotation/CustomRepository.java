package com.zhaojun.annotation;

import java.lang.annotation.*;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:22
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomRepository {

    String value() default "";
}
