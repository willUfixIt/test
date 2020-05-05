/**
 * Project Name:springboot_hotel
 * File Name:UserMapper.java
 * Package Name:cn.java.mapper
 * Date:下午2:23:06
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * Description: <br/>
 * Date: 下午2:23:06 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM users WHERE username=#{arg0} AND pwd=#{arg1}")
    public int login(String username, String pwd);

}
