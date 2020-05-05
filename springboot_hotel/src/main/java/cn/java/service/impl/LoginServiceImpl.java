/**
 * Project Name:springboot_hotel
 * File Name:LoginServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午4:53:20
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.java.entity.OneMenu;
import cn.java.mapper.LoginMapper;
import cn.java.service.LoginService;
import cn.java.utils.MD5;

/**
 * Description: <br/>
 * Date: 下午4:53:20 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Service
@Transactional(readOnly = false)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.LoginService#isLoginSuccess(java.lang.String,
     *      java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Long isLoginSuccess(String username, String pwd) throws Exception {
        // 判断username、pwd是否等于null
        if (username == null || pwd == null) {
            return null;
        }
        // 首先校验用户名与密码是否满足格式要求
        String regex1 = "\\w{3,12}";
        String regex2 = "\\w{6,12}";
        boolean flag1 = username.matches(regex1);
        boolean flag2 = pwd.matches(regex2);
        if (flag1 && flag2) {
            // 然后将密码进行加密，加密后传递给Mapper
            String miWenPwd = MD5.finalMD5(pwd);
            Long flag = loginMapper.login(username, miWenPwd);
            return flag;
        }
        return null;
    }

    @Override
    public List<OneMenu> selectMenusById(Long userId) {
        return loginMapper.getMenusByUserId(userId);
    }

}
