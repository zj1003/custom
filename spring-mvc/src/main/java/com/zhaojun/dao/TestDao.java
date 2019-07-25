package com.zhaojun.dao;

import com.zhaojun.entity.User;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:28
 */

public interface TestDao {

    /**
     * 模拟插入数据
     *
     * @param user
     * @return
     */
    String insert(User user);

}
