/**
 * Project Name:springboot_hotel
 * File Name:HouseMapper.java
 * Package Name:cn.java.mapper
 * Date:下午2:42:21
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Description:客房管理模块。 QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:42:21 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface HouseMapper {

    /**
     * 
     * Description: 查询所有客房信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @Select("SELECT rm.`room_num`,rm.`room_status`,rt.`room_type_name`,rt.`room_price` FROM rooms rm INNER JOIN room_type rt ON rm.`room_type_id`=rt.`id`\r\n"
            + "")
    List<Map<String, Object>> getAllHouseInfo();

    /**
     * 
     * Description: 查询所有的房间类型信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @Select("SELECT * FROM room_type")
    List<Map<String, Object>> getAllRoomType();

    /**
     * 
     * Description: 根据筛选条件查询数据<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param type
     * @param keyword
     * @return
     */
    List<Map<String, Object>> getHouseInfoByCondition(Map<String, Object> paramMap);

    /**
     * 
     * Description: 添加房间类型<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomTypeName
     * @param roomPrice
     * @return
     */
    @Insert("INSERT INTO `room_type` VALUES(NULL,#{arg0},#{arg1})")
    int insertRoomType(String roomTypeName, Float roomPrice);

    /**
     * 
     * Description: 判断房间号是否存在<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @return
     */
    @Select("SELECT COUNT(*) FROM rooms WHERE room_num=#{arg0}")
    int selectRoomNumExist(String roomNum);

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
    @Insert("INSERT INTO rooms VALUES(NULL,#{arg0},#{arg2},#{arg1})")
    int insertRoomInfo(String roomNum, Long roomId, String roomStatus);

}
