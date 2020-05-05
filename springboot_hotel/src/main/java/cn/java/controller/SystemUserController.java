/**
 * Project Name:springboot_hotel
 * File Name:SystemUserController.java
 * Package Name:cn.java.controller
 * Date:下午2:15:12
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.entity.OneMenu;
import cn.java.service.SystemUserService;

/**
 * Description:用户管理模块 QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:15:12 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Controller
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 
     * Description: 用户信息查询<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/getUserInfo.do")
    public String getUserInfo(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, String keyWord, Model model) {
        List<Map<String, Object>> userInfoList = systemUserService.findAllUserInfo(pageNum, pageSize, keyWord);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(userInfoList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/user/userInfo.jsp";
    }

    /**
     * 
     * Description: 修改禁用、启用的状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @param useStaus
     * @return
     */
    @RequestMapping("/updateUseStatus.do")
    public @ResponseBody boolean updateUseStatus(Long id, String useStatus) {
        System.out.println("------------8888888888888888888888-----------------");
        return systemUserService.updateUseStatus(id, useStatus);
    }

    /**
     * 
     * Description: 重置密码<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/resetPwd.do")
    public @ResponseBody boolean resetPwd(Long id, String isAdmin, HttpSession session) throws Exception {
        boolean flag = systemUserService.updatePwd(id);
        if (flag) {
            session.invalidate();
        }
        return flag;
    }

    /**
     * 
     * Description: 跳转到添加用户页面<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/toAddUser.do")
    public String toAddUser(Model model) {
        List<OneMenu> oneMenuList = systemUserService.findAllAuthority();
        model.addAttribute("oneMenuList", oneMenuList);
        return "admin/user/addUser.jsp";
    }

    @RequestMapping("/addUser.do")
    public @ResponseBody boolean addUser(String username, String password, String oneIds, String twoIds)
            throws Exception {
        return systemUserService.saveUser(username, password, oneIds, twoIds);
    }

}
