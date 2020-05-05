/**
 * Project Name:springboot_hotel
 * File Name:InRoomInfoServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午8:30:22
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JieMoney;
import cn.java.mapper.InRoomInfoMapper;
import cn.java.service.InRoomInfoService;

/**
 * Description: <br/>
 * Date: 下午8:30:22 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl implements InRoomInfoService {

    @Autowired
    private InRoomInfoMapper inRoomInfoMapper;

    @Override
    public List<Map<String, Object>> selectAllInRoomInfos(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return inRoomInfoMapper.getAllInRoomInfos();
    }

    @Override
    public List<Map<String, Object>> selectInRoomInfoByCondition(String type, String keyWord) {
        return inRoomInfoMapper.getInRoomInfoByCondition(type, keyWord);
    }

    @Override
    public boolean delById(Long id) {
        return inRoomInfoMapper.delInfoById(id) >= 1 ? true : false;
    }

    @Override
    public boolean batchDel(String idAttr) {// "1,2,"
        idAttr = idAttr.substring(0, idAttr.length() - 1);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idAttr", idAttr);
        int flag = inRoomInfoMapper.batchDel(paramMap);
        return flag >= 1 ? true : false;
    }

    @Override
    public List<Map<String, Object>> getAllKXRoom() {
        return inRoomInfoMapper.getAllKXRoom();
    }

    @Override
    public boolean saveInRoomInfo(InRoomInfo info) {
        // 根据roomNum查出房间对应的房间id
        Long roomId = inRoomInfoMapper.getRoomIdByRoomNum(info.getRoomNum());
        // 将数据保存到in_room_info表中去
        info.setRoomId(roomId);
        int flag = inRoomInfoMapper.addInRoomInfo(info);
        // 修改房间的状态
        if (flag >= 1) {
            return inRoomInfoMapper.updateRoomStatus(info.getRoomNum()) >= 1 ? true : false;
        }
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Map<String, Object>> selectRoomsByStatus() {
        return inRoomInfoMapper.getRoomsByStatus();
    }

    @Transactional(readOnly = false)
    @Override
    public Map<String, Object> findInRoomInfoByRoomId(Long roomId) {
        // 查询出入住信息
        Map<String, Object> roomInfoMap = inRoomInfoMapper.getInRoomInfoByRoomId(roomId);
        // 查询出房间对应的单间
        Float price = inRoomInfoMapper.getRoomPriceByRoomId(roomId);
        roomInfoMap.put("price", price);
        // 查询出其他消费金额
        Float otherMoney = inRoomInfoMapper.getOtherMoney(roomId);
        roomInfoMap.put("otherMoney", otherMoney);
        return roomInfoMap;
    }

    @Override
    public Float jieMoney(JieMoney money) throws Exception {
        // 判断是否是会员
        Float vipRate = inRoomInfoMapper.getVipRateByIdCard(money.getRoomId());
        // 计算入住的时间差
        // 入住时间
        String inRoomDate = money.getDate1();// 2016-06-01
        // 退房时间
        String outRoomDate = money.getDate2();// 2018-10-29 10:31:15
        // 计算入住时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(inRoomDate);
        Date date2 = sdf.parse(outRoomDate);
        int days = (int) ((date2.getTime() - date1.getTime()) / (24 * 3600 * 1000));
        String hour = outRoomDate.split("\\ ")[1].split("\\:")[0];
        int h = Integer.parseInt(hour);
        if (h >= 12) {
            days++;
        }
        // 修改房间的状态
        int flag = inRoomInfoMapper.updateRoomStatusByRoomId(money.getRoomId());
        // 修改退房状态
        inRoomInfoMapper.updateOutRoomStatusByRoomId(money.getRoomId());
        // 计算结算金额
        Float qiTa = money.getQita() == null ? 0F : money.getQita();
        if (vipRate == null || vipRate == 0F) {// 不是会员
            Float jieMoney = money.getPrice() * days + qiTa - money.getYajin();
            return jieMoney;
        }
        // 是会员
        return money.getPrice() * vipRate * days + qiTa - money.getYajin();
    }

}
