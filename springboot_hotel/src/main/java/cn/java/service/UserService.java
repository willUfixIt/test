/**
 * Project Name:springboot_hotel
 * File Name:UserService.java
 * Package Name:cn.java.service.impl
 * Date:下午2:26:55
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

/**
 * Description:	   <br/>
 * Date:     下午2:26:55 <br/>
 * @author   丁鹏
 * @version  
 * @see 	 
 */
public interface UserService {

    /**
     * 
     * Description: 登录业务<br/>
     *
     * @author 丁鹏
     * @param username
     * @param pwd
     * @return
     */
    boolean isLogin(String username, String pwd);

}
