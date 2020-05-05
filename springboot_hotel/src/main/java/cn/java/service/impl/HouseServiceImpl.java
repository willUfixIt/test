/**
 * Project Name:springboot_hotel
 * File Name:HouseServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午2:44:09
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.java.mapper.HouseMapper;
import cn.java.service.HouseService;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:44:09 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Service
@Transactional(readOnly = false)
@CacheConfig(cacheNames = { "HouseServiceImplCache" })
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Map<String, Object>> findAllHouseInfo(Integer pageNum, Integer pageSize) {
        // PageHelper后台分页
        PageHelper.startPage(pageNum, pageSize);
        return houseMapper.getAllHouseInfo();
    }

    @Cacheable(key = "'findAllRoomType'")
    @Override
    public List<Map<String, Object>> findAllRoomType() {
        return houseMapper.getAllRoomType();
    }

    @Override
    public List<Map<String, Object>> findHouseInfoByCondition(Integer pageNum, Integer pageSize, String type,
            String keyword) {
        // 将单个参数封装套Map集合中去
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", type);
        paramMap.put("keyword", keyword);
        // 后台分页
        PageHelper.startPage(pageNum, pageSize);
        return houseMapper.getHouseInfoByCondition(paramMap);
    }

    @Override
    public List<Map<String, Object>> findAllRoomTypeByCondition(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return houseMapper.getAllRoomType();
    }

    @CacheEvict(key = "'findAllRoomType'")
    @Override
    public boolean saveRoomType(String roomTypeName, Float roomPrice) {
        // 先判断数据格式是否完全满足要求
        String regex1 = "[\\u4e00-\\u9fa5]{3,10}";
        if (roomTypeName == null || roomPrice == null) {
            return false;
        }
        // 满足要求
        if (roomTypeName.matches(regex1) && roomPrice > 0F) {
            int result = houseMapper.insertRoomType(roomTypeName, roomPrice);
            return result >= 1 ? true : false;
        }
        // 不满足要求
        return false;
    }

    @Override
    public boolean findRoomNumExist(String roomNum) {
        if (roomNum == null || roomNum == "" || (roomNum != null && roomNum.trim().equals(""))) {
            return true;
        } else {
            int result = houseMapper.selectRoomNumExist(roomNum);
            return result >= 1 ? true : false;
        }
    }

    @Override
    public boolean saveRoomInfo(String roomNum, Long roomId, String roomStatus) {
        // 先校验数据
        if (roomNum == null || roomId == null || roomStatus == null) {
            return false;
        }
        if (!(roomStatus.matches("[012]"))) {
            return false;
        }
        return houseMapper.insertRoomInfo(roomNum, roomId, roomStatus) >= 1 ? true : false;
    }

}
