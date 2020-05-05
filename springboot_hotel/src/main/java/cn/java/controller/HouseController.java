/**
 * Project Name:springboot_hotel
 * File Name:HouseController.java
 * Package Name:cn.java.controller
 * Date:下午2:32:48
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.service.HouseService;

/**
 * Description: 客房管理。QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:32:48 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Controller
public class HouseController {
    @Autowired
    private HouseService houseService;

    /**
     * 
     * Description: 跳转到客房查询页面<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getHouseInfo.do")
    public String getHouseInfo(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Model model) {
        List<Map<String, Object>> houseList = houseService.findAllHouseInfo(pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(houseList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/room/houseInfo.jsp";
    }

    /**
     * 
     * Description: 获取房间类型信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/getRoomType.do")
    public @ResponseBody List<Map<String, Object>> getRoomType() {
        return houseService.findAllRoomType();
    }

    /**
     * 
     * Description: 按照条件查询<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param type
     * @param keyword
     * @return
     */
    @RequestMapping("/getHouseInfoByCondition.do")
    public String getHouseInfoByCondition(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, String type, String keyword,
            Model model) {
        List<Map<String, Object>> resultList = houseService.findHouseInfoByCondition(pageNum, pageSize, type, keyword);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(resultList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/room/houseInfo.jsp";
    }

    /**
     * 
     * Description: 客房信息管理<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/getHouseManage.do")
    public String getHouseManage(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Model model) {
        List<Map<String, Object>> roomTypeList = houseService.findAllRoomType();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(roomTypeList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/room/houseManage.jsp";
    }

    /**
     * 
     * Description: 添加房间类型<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomTypeName
     * @param roomPrice
     * @return
     */
    @RequestMapping("/addRoomType.do")
    public @ResponseBody boolean addRoomType(String roomTypeName, Float roomPrice) {
        return houseService.saveRoomType(roomTypeName, roomPrice);
    }

    /**
     * 
     * Description: 判断指定的房间号是否存在<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @return
     */
    @RequestMapping("/isRoomNumExist.do")
    public @ResponseBody boolean isRoomNumExist(String roomNum) {
        return houseService.findRoomNumExist(roomNum);
    }

    /**
     * 
     * Description: 添加客房<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @RequestMapping("/addRoom.do")
    public String addRoom(String roomNum, Long roomId, String roomStatus) {
        boolean result = houseService.saveRoomInfo(roomNum, roomId, roomStatus);
        if (result) {// 添加成功
            return "redirect:/getHouseInfo.do";
        }
        return "admin/room/addRoom.jsp";
    }
}
