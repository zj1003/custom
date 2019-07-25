package com.zhaojun.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhaoJun
 * @date 2019/7/25 14:56
 */

public interface ArgumentResolver {

    /**
     * 是否支持
     *
     * @param clazz
     * @return
     */
    boolean isSupport(Class clazz);

    /**
     * 真正的处理方法
     *
     * @param req
     * @param resp
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    Object resolve(HttpServletRequest req, HttpServletResponse resp, Class clazz)
        throws IllegalAccessException, InstantiationException;

}
