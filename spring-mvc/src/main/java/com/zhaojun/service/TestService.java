package com.zhaojun.service;

import com.zhaojun.annotation.CustomAutowired;
import com.zhaojun.annotation.CustomService;
import com.zhaojun.dao.TestDao;
import com.zhaojun.entity.User;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:28
 */
@CustomService("testService")
public class TestService {

    @CustomAutowired
    private TestDao testDao;

    public String insert(User user) {
        return testDao.insert(user);
    }


}
