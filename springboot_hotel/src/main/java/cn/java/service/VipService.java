/**
 * Project Name:springboot_hotel
 * File Name:VipService.java
 * Package Name:cn.java.service.impl
 * Date:上午10:30:49
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.java.entity.Vip;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午10:30:49 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface VipService {

    /**
     * 
     * Description: 查询数据库vip表中的所有信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    List<Map<String, Object>> findAllVipInfo(Integer pageNum, Integer pageSize);

    /**
     * 
     * Description: 添加会员信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param vip
     * @return
     */
    boolean saveVipInfo(Vip vip, HttpServletRequest request);

    /**
     * 
     * Description:根据主键查询详细的vip会员信息 <br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @return
     */
    Map<String, Object> findVipInfoById(Long id);

    /**
     * 
     * Description: 修改会员信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param vip
     * @return
     */
    boolean updateVipInfo(Vip vip);

}
