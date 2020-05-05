/**
 * Project Name:springboot_hotel
 * File Name:VipMapper.java
 * Package Name:cn.java.mapper
 * Date:上午10:26:41
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.java.entity.Vip;

/**
 * Description: 会员管理模块QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午10:26:41 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface VipMapper {

    /**
     * 
     * Description: 查询数据库vip表中的所有信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    @Select("SELECT * FROM vip")
    List<Map<String, Object>> getAllVipInfo();

    /**
     * 
     * Description: 判断某个会员是否存在<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param idcard
     * @return
     */
    @Select("SELECT COUNT(*) FROM `vip` WHERE idcard=#{arg0}")
    int isVipExist(String idcard);

    /**
     * 
     * Description: 添加会员信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param vip
     * @return
     */
    @Insert("INSERT INTO vip VALUES(null,#{vipNum},#{customerName},#{vipRate},#{idcard},#{phone},#{createDate},#{gender})")
    int addVipInfo(Vip vip);

    /**
     * 
     * Description:根据主键查询详细的vip会员信息 <br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @return
     */
    @Select("SELECT * FROM `vip` WHERE id=#{arg0}")
    Map<String, Object> getVipInfoById(Long id);

    /**
     * 
     * Description: 修改会员信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param vip
     * @return
     */
    @Insert("UPDATE vip SET customer_name=#{customerName},gender=#{gender},idcard=#{idcard},phone=#{phone},vip_rate=#{vipRate} WHERE id=#{id}")
    int updateVipInfo(Vip vip);

}
