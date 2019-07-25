package com.zhaojun.resolver.impl;

import com.zhaojun.annotation.CustomService;
import com.zhaojun.entity.User;
import com.zhaojun.resolver.ArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * @author ZhaoJun
 * @date 2019/7/25 15:01
 */
@CustomService
public class UserBeanAttributeArgResolver implements ArgumentResolver {

    @Override
    public boolean isSupport(Class clazz) {
        //目前只做user类的判断
        return clazz.equals(User.class);
    }

    @Override
    public Object resolve(HttpServletRequest req, HttpServletResponse resp, Class clazz)
        throws InstantiationException, IllegalAccessException {
        Object o = clazz.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object value;
            String parameter = req.getParameter(field.getName());
            if (fieldType.equals(Integer.class)) {
                value = Integer.valueOf(parameter);
            } else {
                value = parameter;
            }
            field.set(o, value);
        }
        return o;
    }
}
