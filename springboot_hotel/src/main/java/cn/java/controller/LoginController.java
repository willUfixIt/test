/**
 * Project Name:springboot_hotel
 * File Name:LoginController.java
 * Package Name:cn.java.controller
 * Date:下午4:59:46
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.java.entity.OneMenu;
import cn.java.service.LoginService;

/**
 * Description: <br/>
 * Date: 下午4:59:46 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 
     * Description: 登录<br/>
     *
     * @author 丁鹏
     * @param username从Jsp页面接收
     * @param pwd从Jsp页面接收
     * @return
     * @throws Exception
     */
    @RequestMapping("/login.do")
    public String login(String username, String pwd, HttpSession session) throws Exception {
        System.out.println(username + "-------------------" + pwd);
        // 调用业务方法
        Long flag = loginService.isLoginSuccess(username, pwd);
        // 根据业务方法返回的结果，决定跳转页面
        if (flag != null || flag != 0) {// flag=true
            session.setAttribute("username", username);
            // 获取菜单
            List<OneMenu> oneMenuList = loginService.selectMenusById(flag);
            session.setAttribute("oneMenuList", oneMenuList);
            return "redirect:/pages/admin/index.jsp";
            // return "admin/index.jsp";
        } else {// flag=false
            return "redirect:/pages/admin/login.jsp";
            // return "admin/login.jsp";
        }
    }

}
