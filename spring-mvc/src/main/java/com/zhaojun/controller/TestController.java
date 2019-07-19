package com.zhaojun.controller;

import com.zhaojun.annotation.CustomAutowired;
import com.zhaojun.annotation.CustomController;
import com.zhaojun.annotation.CustomRequestMapping;
import com.zhaojun.entity.User;
import com.zhaojun.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:23
 */
@CustomController
@CustomRequestMapping("/test")
public class TestController {

    @CustomAutowired
    private TestService testService;

    @CustomRequestMapping("/insert")
    public void test(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String result = testService.insert(user);
        writer.write(result);
        writer.flush();
        writer.close();
    }


}
