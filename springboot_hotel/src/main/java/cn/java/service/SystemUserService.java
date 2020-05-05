/**
 * Project Name:springboot_hotel
 * File Name:SystemUserService.java
 * Package Name:cn.java.service
 * Date:下午2:22:36
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.service;

import java.util.List;
import java.util.Map;

import cn.java.entity.OneMenu;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:22:36 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public interface SystemUserService {
    /**
     * 
     * Description: 查询所有系统用户信息<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @return
     */
    List<Map<String, Object>> findAllUserInfo(Integer pageNum, Integer pageSize, String keyWord);

    /**
     * 
     * Description: 修改启用，禁用状态<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @param useStaus
     * @return
     */
    boolean updateUseStatus(Long id, String useStaus);

    /**
     * 
     * Description: 重置或者修改密码<br/>
     *
     * @author 丁鹏(大胆开车，幽默讲课)
     * @param id
     * @param pwd
     * @return
     */
    boolean updatePwd(Long id) throws Exception;

    /**
     * 
     * Description: 获取数据库中所有的权限<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<OneMenu> findAllAuthority();

    boolean saveUser(String username, String password, String oneIds, String twoIds) throws Exception;
}
