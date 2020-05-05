/**
 * Project Name:springboot_hotel
 * File Name:UserServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午2:24:36
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.UserMapper;
import cn.java.service.UserService;

/**
 * Description: <br/>
 * Date: 下午2:24:36 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.UserService#isLogin(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean isLogin(String username, String pwd) {
        return userMapper.login(username, pwd) >= 1 ? true : false;
    }

}
