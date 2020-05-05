/**
 * Project Name:springboot_hotel
 * File Name:LoginService.java
 * Package Name:cn.java.service.impl
 * Date:下午4:58:31
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

import java.util.List;

import cn.java.entity.OneMenu;

/**
 * Description: <br/>
 * Date: 下午4:58:31 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface LoginService {

    /**
     * 
     * Description: 登录业务方法<br/>
     *
     * @author 丁鹏
     * @param username
     * @param pwd 明文
     * @return
     * @throws Exception
     */
    Long isLoginSuccess(String username, String pwd) throws Exception;

    /**
     * 
     * Description: 获取system_authority中的所有菜单<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<OneMenu> selectMenusById(Long userId);

}
