/**
 * Project Name:springboot_hotel
 * File Name:HouseService.java
 * Package Name:cn.java.service
 * Date:下午2:43:32
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

import java.util.List;
import java.util.Map;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:43:32 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface HouseService {
    /**
     * 
     * Description: 查询所有客房信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    public List<Map<String, Object>> findAllHouseInfo(Integer pageNum, Integer pageSize);

    /**
     * 
     * Description: 查询所有的房间类型信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    public List<Map<String, Object>> findAllRoomType();

    /**
     * 
     * Description: 根据筛选条件查询数据<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param type
     * @param keyword
     * @return
     */
    public List<Map<String, Object>> findHouseInfoByCondition(Integer pageNum, Integer pageSize, String type,
            String keyword);

    /**
     * 
     * Description: 查询所有的房间类型信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    public List<Map<String, Object>> findAllRoomTypeByCondition(Integer pageNum, Integer pageSize);

    /**
     * 
     * Description: 添加房间类型<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomTypeName
     * @param roomPrice
     * @return
     */
    public boolean saveRoomType(String roomTypeName, Float roomPrice);

    /**
     * 
     * Description: 判断房间号是否存在<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @return
     */
    public boolean findRoomNumExist(String roomNum);

    /**
     * 
     * Description: 添加房间信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @param roomId
     * @param roomStatus
     * @return
     */
    boolean saveRoomInfo(String roomNum, Long roomId, String roomStatus);
}
