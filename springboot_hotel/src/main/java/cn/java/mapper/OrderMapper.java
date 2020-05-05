/**
 * Project Name:springboot_hotel
 * File Name:OrderMapper.java
 * Package Name:cn.java.mapper
 * Date:下午2:42:11
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.java.entity.Order;

/**
 * Description: 订单管理模块。QQ：870599752(加好友时记得备注哦) Date: 下午2:42:11 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface OrderMapper {
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
     * Description: 添加订单<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param order
     * @return
     */
    @Insert("INSERT INTO `orders`  VALUES(NULL,#{orderNum},#{money},#{orderStatus},#{roomId},#{createDate})")
    int addOrder(Order order);

    /**
     * 
     * Description: 查询所有订单信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @Select(value = "SELECT o.*,iri.customer_name,rs.room_num FROM `orders` o INNER JOIN in_room_info iri ON o.room_id=iri.room_id INNER JOIN rooms rs ON rs.id=o.room_id\r\n"
            + "")
    List<Map<String, Object>> getAllOrders();
}
