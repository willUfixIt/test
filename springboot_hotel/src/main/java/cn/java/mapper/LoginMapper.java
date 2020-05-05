/**
 * Project Name:springboot_hotel
 * File Name:LoginMapper.java
 * Package Name:cn.java.mapper
 * Date:下午4:50:55
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.OneMenu;

/**
 * Description: 登陸模块<br/>
 * Date: 下午4:50:55 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface LoginMapper {

    /**
     * 
     * Description: 登录<br/>
     *
     * @author 丁鹏
     * @param username 账号
     * @param pwd 密文
     * @return
     */
    @Select("SELECT id FROM SYSTEM_USER WHERE username=#{arg0} AND pwd=#{arg1}")
    Long login(String username, String pwd);

    /**
     * 
     * Description: 获取system_authority中的所有菜单<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<OneMenu> getMenusByUserId(Long userId);

}
