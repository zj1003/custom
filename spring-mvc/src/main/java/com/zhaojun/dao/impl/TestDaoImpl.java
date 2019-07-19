package com.zhaojun.dao.impl;

import com.zhaojun.annotation.CustomRepository;
import com.zhaojun.dao.TestDao;
import com.zhaojun.entity.User;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:29
 */

@CustomRepository("testDao")
public class TestDaoImpl implements TestDao {

    @Override
    public String insert(User user) {
        String result = "数据插入成功，插入数据为：" + user;
        System.out.println(result);
        return result;
    }
}
