/**
 * Project Name:springboot_hotel
 * File Name:InRoomInfoMapper.java
 * Package Name:cn.java.mapper
 * Date:下午8:26:41
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;
/**
 * Description:	   <br/>
 * Date:     下午8:26:41 <br/>
 * @author   丁鹏
 * @version  
 * @see 	 
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.InRoomInfo;

public interface InRoomInfoMapper {

    /**
     * 
     * Description: 分页查询入住信息：870599752<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<Map<String, Object>> getAllInRoomInfos();

    /**
     * 
     * Description: 入住信息查询中的条件查询<br/>
     *
     * @author 丁鹏
     * @param type
     * @param keyWord
     * @return
     */
    List<Map<String, Object>> getInRoomInfoByCondition(String type, String keyWord);

    /**
     * 
     * Description: 删除入住信息<br/>
     *
     * @author 丁鹏
     * @param id
     * @return
     */
    @Update("UPDATE in_room_info SET status='0' WHERE id=#{arg0}")
    int delInfoById(Long id);

    /**
     * 
     * Description: 批量删除<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param paramMap
     * @return
     */
    @Update("UPDATE in_room_info SET `status`='0' WHERE id IN(${idAttr})")
    int batchDel(Map<String, Object> paramMap);

    /**
     * 
     * Description: 获取所有的空闲房间<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)：QQ870599752
     * @return
     */
    @Select("SELECT * FROM rooms WHERE room_status='0'")
    List<Map<String, Object>> getAllKXRoom();

    /**
     * 
     * Description: 根据房间号获取房间的id<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @return
     */
    @Select(value = "SELECT id FROM `rooms` WHERE room_num=#{arg0}")
    Long getRoomIdByRoomNum(String roomNum);

    /**
     * 
     * Description: 添加入住信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param info
     * @return
     */
    @Insert("INSERT INTO `in_room_info` VALUES(NULL,#{customerName},#{gender},#{isVip},#{idcard},#{phone},#{money},#{createDate},#{roomId},'1')")
    int addInRoomInfo(InRoomInfo info);

    /**
     * 
     * Description: 修改房间的状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomNum
     * @return
     */
    @Update("UPDATE `rooms` SET room_status='1' WHERE room_num=#{arg0}")
    int updateRoomStatus(String roomNum);

    /**
     * 
     * Description: 获取数据库表中所有已经入住的房间号<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @Select("SELECT room_num,id FROM `rooms` WHERE room_status='1'")
    List<Map<String, Object>> getRoomsByStatus();

    /**
     * 
     * Description: 根据房间主键来获取入住人的详细信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    // @Select("SELECT * FROM `in_room_info` WHERE room_id=#{arg0} LIMIT 1")
    @Select("SELECT customer_name,gender,idcard,phone,money,DATE_FORMAT(create_date,'%Y-%m-%d') AS create_date FROM `in_room_info` WHERE room_id=#{arg0} LIMIT 1\r\n"
            + "")
    Map<String, Object> getInRoomInfoByRoomId(Long roomId);

    /**
     * 
     * Description: 根据房间主键查询出房间对应的单价<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @return
     */
    @Select("SELECT room_price FROM room_type WHERE id=(SELECT room_type_id FROM `rooms` WHERE id=#{arg0})")
    Float getRoomPriceByRoomId(Long id);

    /**
     * 
     * Description: 根据房间的ID计算客人的其他消费金额<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    @Select("SELECT SUM(order_money) FROM orders WHERE room_id=#{arg0} AND order_status='0'")
    Float getOtherMoney(Long roomId);

    /**
     * 
     * Description: 根据身份证查看客人是否是vip<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    @Select("SELECT vip_rate FROM vip WHERE idcard=(SELECT idcard FROM in_room_info WHERE room_id=#{arg0})")
    Float getVipRateByIdCard(Long roomId);

    /**
     * 
     * Description: 修改房间状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    @Update("UPDATE rooms SET room_status='2' WHERE id=#{arg0}")
    int updateRoomStatusByRoomId(Long roomId);

    /**
     * 
     * Description: 修改退房状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param roomId
     * @return
     */
    @Update("update in_room_info set out_room_status='1' where room_id=#{arg0}")
    int updateOutRoomStatusByRoomId(Long roomId);

}
