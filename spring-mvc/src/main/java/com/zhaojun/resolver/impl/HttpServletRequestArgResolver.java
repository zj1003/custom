package com.zhaojun.resolver.impl;

import com.zhaojun.annotation.CustomService;
import com.zhaojun.resolver.ArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhaoJun
 * @date 2019/7/25 14:57
 */
@CustomService
public class HttpServletRequestArgResolver implements ArgumentResolver {

    @Override
    public boolean isSupport(Class clazz) {
        return clazz.equals(HttpServletRequest.class);
    }

    @Override
    public Object resolve(HttpServletRequest req, HttpServletResponse resp, Class clazz) {
        return req;
    }
}
