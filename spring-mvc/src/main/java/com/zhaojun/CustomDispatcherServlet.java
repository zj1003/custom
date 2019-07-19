package com.zhaojun;

import com.zhaojun.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:15
 */

public class CustomDispatcherServlet extends HttpServlet {

    private final List<String> classNames= new ArrayList<>();
    private final Map<String, Object> beanMap = new HashMap<>();
    private final Map<String, Object> handlerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        scanPackage("com.zhaojun");
        try {
            ioc();
            di();
        } catch (Exception e) {
            e.printStackTrace();
        }
        handMap();
        //System.out.println(beanMap);
        //System.out.println(handlerMap);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replaceAll(contextPath, "");
        Method method = (Method)handlerMap.get(path);
        if (method == null) {
            System.out.println("404  没有找到");
        }
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            Class<?> parameterType = parameter.getType();
            String parameterName = parameter.getName();


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void scanPackage(String basePackage) {
        //file:/D:/demo/custom/spring-mvc/target/spring-mvc/WEB-INF/classes/
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        String path = url.getPath();
        File baseFile = new File(path);
        File[] files = baseFile.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                scanPackage(basePackage + "." + fileName);
            } else {
                String className = (basePackage + "." + fileName).replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    public void ioc() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String className : classNames) {
            Class clazz = Class.forName(className);
            //controller
            if (clazz.isAnnotationPresent(CustomController.class)) {
                if (clazz.isAnnotationPresent(CustomRequestMapping.class)) {
                    CustomRequestMapping annotation =
                        (CustomRequestMapping)clazz.getAnnotation(CustomRequestMapping.class);
                    beanMap.put(annotation.value(),clazz.newInstance());
                }
            }
            //service
            if (clazz.isAnnotationPresent(CustomService.class)) {
                CustomService annotation = (CustomService)clazz.getAnnotation(CustomService.class);
                beanMap.put(annotation.value(), clazz.newInstance());
            }
            //repository
            if (clazz.isAnnotationPresent(CustomRepository.class)) {
                CustomRepository annotation = (CustomRepository)clazz.getAnnotation(CustomRepository.class);
                beanMap.put(annotation.value(), clazz.newInstance());
            }
        }
    }

    public void di() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                field.setAccessible(true);
                for (Annotation annotation : annotations) {
                    if (annotation.equals(CustomService.class)) {
                        field.set(entry.getValue(), beanMap.get(((CustomService)annotation).value()));
                    }
                    if (annotation.equals(CustomRepository.class)) {
                        field.set(entry.getValue(), beanMap.get(((CustomRepository)annotation).value()));
                    }
                }
            }
        }
    }

    private void handMap() {
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(CustomRequestMapping.class)) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(CustomRequestMapping.class)) {
                        CustomRequestMapping annotation = method.getAnnotation(CustomRequestMapping.class);
                        handlerMap.put(entry.getKey() + annotation.value(), method);
                    }
                }
            }

        }
    }
}
