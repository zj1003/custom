package com.zhaojun.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhaoJun
 * @date 2019/7/25 14:56
 */

public interface ArgumentResolver {

    boolean isSupport(Class clazz);

    Object resolve(HttpServletRequest req, HttpServletResponse resp, Class clazz) throws IllegalAccessException,
        InstantiationException;

}
