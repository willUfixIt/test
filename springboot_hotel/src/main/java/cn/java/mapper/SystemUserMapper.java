/**
 * Project Name:springboot_hotel
 * File Name:SystemUserMapper.java
 * Package Name:cn.java.mapper
 * Date:下午2:21:28
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.mapper;
/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦)
 * Date:     下午2:21:28 <br/>
 * @author   丁鹏(大胆开车，幽默讲课)
 * @version  
 * @see 	 
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.OneMenu;

public interface SystemUserMapper {

    /**
     * 
     * Description: 查询所有系统用户信息与分页查询<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    List<Map<String, Object>> getAllUserInfo(String keyWord);

    /**
     * 
     * Description: 修改启用，禁用状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @param useStaus
     * @return
     */
    @Update("UPDATE SYSTEM_USER SET use_status=#{arg1} WHERE id=#{arg0}")
    int updateUseStatus(Long id, String useStatus);

    /**
     * 
     * Description: 重置或者修改密码<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @param pwd
     * @return
     */
    @Update("UPDATE SYSTEM_USER SET pwd=#{arg1} WHERE id=#{arg0}")
    int updatePwd(Long id, String pwd);

    /**
     * 
     * Description: 获取数据库中所有的权限<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<OneMenu> getAllAuthority();

    /**
     * 
     * Description: 插入数据<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param username
     * @param pwd
     * @param createDate
     * @return
     */
    @Insert("INSERT INTO SYSTEM_USER VALUES(NULL,#{arg0},#{arg1},#{arg2},'1','0')")
    int insertSystemUser(String username, String pwd, String createDate);

    /**
     * 
     * Description: 获取用户的主键<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param username
     * @param pwd
     * @return
     */
    @Select("SELECT id FROM SYSTEM_USER WHERE username=#{arg0} AND pwd=#{arg1}")
    Long getSystemUserId(String username, String pwd);

    /**
     * 
     * Description: 添加用户主键与权限的关联关系<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param userId
     * @param authorityId
     * @return
     */
    @Insert("INSERT INTO user_authority VALUES(#{arg0},#{arg1})")
    int insertUserAuthoryRelation(Long userId, Long authorityId);

}
