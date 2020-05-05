/**
 * Project Name:springboot_hotel
 * File Name:InRoomInfoService.java
 * Package Name:cn.java.service
 * Date:下午8:29:51
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

import java.util.List;
import java.util.Map;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JieMoney;

/**
 * Description: <br/>
 * Date: 下午8:29:51 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface InRoomInfoService {
    /**
     * 
     * Description: 分页查询入住信息：QQ870599752<br/>
     *
     * @author 丁鹏
     * @param pageNum：当前页码
     * @param pageSize：每页显示的数据量
     * @return
     */
    List<Map<String, Object>> selectAllInRoomInfos(Integer pageNum, Integer pageSize);

    /**
     * 
     * Description: 入住信息查询中的条件查询<br/>
     *
     * @author 丁鹏
     * @param type
     * @param keyWord
     * @return
     */
    List<Map<String, Object>> selectInRoomInfoByCondition(String type, String keyWord);

    /**
     * 
     * Description: 删除入住信息<br/>
     *
     * @author 丁鹏
     * @param id
     * @return
     */
    boolean delById(Long id);

    /**
     * 
     * Description: 批量删除<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)：870599752
     * @param idAttr
     * @return
     */
    boolean batchDel(String idAttr);

    /**
     * 
     * Description: 获取所有的空闲房间<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)：QQ870599752
     * @return
     */
    List<Map<String, Object>> getAllKXRoom();

    /**
     * 
     * Description: 入住信息添加<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param info
     * @return
     */
    boolean saveInRoomInfo(InRoomInfo info);

    /**
     * 
     * Description: 获取数据库表中所有已经入住的房间号<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    List<Map<String, Object>> selectRoomsByStatus();

    /**
     * 
     * Description: 根据房间主键来获取入住人的详细信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    Map<String, Object> findInRoomInfoByRoomId(Long roomId);

    /**
     * 
     * Description: 结账退房<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param money
     * @return
     */
    Float jieMoney(JieMoney money) throws Exception;
}
