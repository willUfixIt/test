/**
 * Project Name:springboot_hotel
 * File Name:InRoomInfoController.java
 * Package Name:cn.java.controller
 * Date:下午8:14:41
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JieMoney;
import cn.java.service.InRoomInfoService;

/**
 * Description: 入住管理模块<br/>
 * Date: 下午8:14:41 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Controller
public class InRoomInfoController {
    @Autowired
    private InRoomInfoService inRoomInfoService;

    /**
     * 
     * Description: 入住信息查询<br/>
     *
     * @author 丁鹏
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/getInRoomInfo.do")
    public String getInRoomInfo(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize, Model model) {
        // 分页之后返回的数据
        List<Map<String, Object>> infoList = inRoomInfoService.selectAllInRoomInfos(pageNum, pageSize);
        // 将infoList封装到PageInfo工具类中
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(infoList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/bill/inroominfo.jsp";
    }

    /**
     * 
     * Description: 入住信息查询-按照条件具体查询<br/>
     *
     * @author 丁鹏
     * @param type
     * @param keyWord
     * @return
     */
    @RequestMapping("/getInRoomInfoByCondition.do")
    public String getInRoomInfoByCondition(String type, String keyWord, Model model) {
        List<Map<String, Object>> infoList = inRoomInfoService.selectInRoomInfoByCondition(type, keyWord);
        model.addAttribute("infoList", infoList);
        return "admin/bill/inroominfo_condition.jsp";
    }

    @RequestMapping("/delInRoomInfo.do")
    public @ResponseBody boolean delInRoomInfo(Long id) {
        boolean flag = inRoomInfoService.delById(id);
        return flag;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/batchDel.do")
    public @ResponseBody boolean batchDel(String idAttr) {
        return inRoomInfoService.batchDel(idAttr);
    }

    /**
     * 
     * Description: 跳转到入住信息添加<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/checkIn.do")
    public String checkIn(Model model) {
        List<Map<String, Object>> roomList = inRoomInfoService.getAllKXRoom();
        model.addAttribute("roomList", roomList);
        return "admin/bill/checkin.jsp";
    }

    /**
     * 
     * Description: 入住信息添加<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param inRoomInfo
     * @param br
     * @param session
     * @return
     */
    @RequestMapping("/addInRoomInfo.do")
    public String addInRoomInfo(@Valid InRoomInfo inRoomInfo, BindingResult br, HttpSession session) {
        System.out.println("-----------" + inRoomInfo + "-------------");
        boolean flag = br.hasErrors();
        if (flag) {// 数据格式错误
            // 将错误信息封装
            Map<String, Object> errorMap = new HashMap<String, Object>();
            List<FieldError> fieldErrorList = br.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                String field = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errorMap.put(field, errorMessage);
            }
            session.setAttribute("errorMap", errorMap);
            session.setAttribute("inRoomInfo", inRoomInfo);
            return "redirect:/checkIn.do";
        } else {// 所有数据格式完全正确
            // 调用业务层代码，将数据保存到数据库库中
            boolean result = inRoomInfoService.saveInRoomInfo(inRoomInfo);
            if (result) {// 入住信息添加成功
                return "redirect:/getInRoomInfo.do";
            } else {// 入住信息添加失败，重新跳回添加页面
                return "redirect:/checkIn.do";
            }
        }
    }

    /**
     * 
     * Description: 结账退房<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/jieZhang.do")
    public String jieZhang(Model model) {
        // 调用业务层获取所有已经入住的房间号
        List<Map<String, Object>> rmList = inRoomInfoService.selectRoomsByStatus();
        model.addAttribute("rmList", rmList);
        return "admin/bill/out.jsp";
    }

    /**
     * 
     * Description: 根据roomId(房间主键)查询出入住人的详细信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/getInRoomInfoByRoomId.do")
    public @ResponseBody Map<String, Object> getInRoomInfoByRoomId(Long roomId) {
        Map<String, Object> roomInfoMap = inRoomInfoService.findInRoomInfoByRoomId(roomId);
        System.out.println(roomInfoMap);
        return roomInfoMap;
    }

    /**
     * 
     * Description: 结账退房<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param money
     * @return
     * @throws Exception
     */
    @RequestMapping("/jieMoney.do")
    public @ResponseBody Float jieMoney(JieMoney money) throws Exception {
        System.out.println(money);
        Float jieMoney = inRoomInfoService.jieMoney(money);
        return jieMoney;
    }

}
