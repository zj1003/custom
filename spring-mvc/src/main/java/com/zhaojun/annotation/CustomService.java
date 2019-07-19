package com.zhaojun.annotation;

import java.lang.annotation.*;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:21
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomService {

    String value() default "";

}
