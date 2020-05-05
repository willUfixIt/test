/**
 * Project Name:springboot_hotel
 * File Name:SystemUserServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午2:23:15
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.java.entity.OneMenu;
import cn.java.mapper.SystemUserMapper;
import cn.java.service.SystemUserService;
import cn.java.utils.MD5;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:23:15 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Service
@Transactional(readOnly = false)
@CacheConfig(cacheNames = { "SystemUserServiceImplCache" })
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Map<String, Object>> findAllUserInfo(Integer pageNum, Integer pageSize, String keyWord) {
        PageHelper.startPage(pageNum, pageSize);
        return systemUserMapper.getAllUserInfo(keyWord);
    }

    @Override
    public boolean updateUseStatus(Long id, String useStatus) {
        if (useStatus == null) {
            return false;
        }
        if (!(useStatus.matches("[01]"))) {
            return false;
        }
        return systemUserMapper.updateUseStatus(id, useStatus) >= 1 ? true : false;
    }

    @Override
    public boolean updatePwd(Long id) throws Exception {
        // 每次重置，默认密码为123456
        String pwd = MD5.finalMD5("123456");
        return systemUserMapper.updatePwd(id, pwd) >= 1 ? true : false;
    }

    @Cacheable(key = "'findAllAuthority'")
    @Override
    public List<OneMenu> findAllAuthority() {
        return systemUserMapper.getAllAuthority();
    }

    @Override
    public boolean saveUser(String username, String password, String oneIds, String twoIds) throws Exception {
        password = MD5.finalMD5(password);
        // 往system_user表中添加数据
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int flag = systemUserMapper.insertSystemUser(username, password, sdf.format(date));
        // 获取刚添加完用户的id值
        if (flag < 1) {
            return false;
        }
        Long userId = systemUserMapper.getSystemUserId(username, password);
        // 往user_authority表中添加映射关系
        // 再添加用户与一级菜单的映射关系
        String[] oneIdsAttr = oneIds.split("\\,");
        // 取出重复一级id的值
        Set<String> oneIdSet = new HashSet<String>();
        for (String temp : oneIdsAttr) {
            oneIdSet.add(temp);
        }
        // 开始插入一级与用户的映射关系
        for (String temp : oneIdSet) {
            systemUserMapper.insertUserAuthoryRelation(userId, Long.parseLong(temp));
        }
        // 先添加用户与二级菜单的映射关系
        String[] twoIdAtr = twoIds.split("\\,");
        for (String temp : twoIdAtr) {
            systemUserMapper.insertUserAuthoryRelation(userId, Long.parseLong(temp));
        }
        return true;
    }

}
