/**
 * Project Name:springboot_hotel
 * File Name:VipController.java
 * Package Name:cn.java.controller
 * Date:上午10:12:54
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.java.entity.Vip;
import cn.java.service.VipService;

/**
 * Description: 会员管理模块。QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午10:12:54 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Controller
public class VipController {
    @Autowired
    private VipService vipService;

    /**
     * 
     * Description: 当点击会员信息查询时，跳转套jsp页面<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param pageNum：页码
     * @param pageSize：每页显示的数据条数
     * @param request
     * @return
     */
    @RequestMapping("/getAllVip.do")
    public String getAllVip(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize, HttpServletRequest request) {
        // 调用业务层获取vip的所有信息
        List<Map<String, Object>> vipInfoList = vipService.findAllVipInfo(pageNum, pageSize);
        // 将数据封装套PageInfo工具类中去
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(vipInfoList);
        request.setAttribute("pageInfo", pageInfo);
        return "admin/vip/vip.jsp";
    }

    @RequestMapping("/addVipInfo.do")
    public String addVipInfo(@Valid Vip vip, BindingResult br, HttpServletRequest request) throws InterruptedException {
        boolean flag = br.hasErrors();
        if (flag) {// 数据格式有错误
            List<FieldError> fieldErrorList = br.getFieldErrors();
            // 创建一个Map集合封装错误信息
            Map<String, Object> errorMap = new HashMap<String, Object>();
            for (FieldError fieldError : fieldErrorList) {
                // 获取发生错误的字段属性名
                String fieldName = fieldError.getField();
                // 获取错误信息
                String errorMessage = fieldError.getDefaultMessage();
                errorMap.put(fieldName, errorMessage);
            }
            request.setAttribute("errorMap", errorMap);
            request.setAttribute("vip", vip);
            return "admin/vip/addvip.jsp";
        } else {// 数据格式完全正确，可以调用业务层方法将数据保存到数据库中去了
            // 调用业务层
            boolean result = vipService.saveVipInfo(vip, request);
            // 跳转到VIP信息查询页面
            if (result) {
                return "redirect:/getAllVip.do";
            } else {// 添加失败
                return "admin/vip/addvip.jsp";
            }
        }
    }

    /**
     * 
     * Description: 根据会员id获取vip详细信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @return
     */
    @RequestMapping("/getVipInfoById.do")
    public String getVipInfoById(Long id, Model model) {
        // 调用业务层
        Map<String, Object> vipInfoMap = vipService.findVipInfoById(id);
        // 跳转到修改页面，并且带数据过去
        model.addAttribute("vipInfoMap", vipInfoMap);
        return "admin/vip/updateVipInfo.jsp";
    }

    /**
     * 
     * Description: 修改会员信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param vip
     * @return
     */
    @RequestMapping("/updateVipInfo.do")
    public String updateVipInfo(Vip vip) {
        boolean flag = vipService.updateVipInfo(vip);
        if (flag) {// 修改成功
            return "redirect:/getAllVip.do";
        }
        return "redirect:/getVipInfoById.do";
    }

}
