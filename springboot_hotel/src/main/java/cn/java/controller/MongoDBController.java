/**
 * Project Name:springboot_hotel
 * File Name:MongoDBController.java
 * Package Name:cn.java.controller
 * Date:上午11:02:33
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.java.entity.Student;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午11:02:33 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
@Controller
public class MongoDBController {
    @Autowired
    private MongoOperations mongoOperations;

    @RequestMapping("/insertDataToMongoDB.do")
    public void insertDataToMongoDB() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 200000; i++) {
            Student student1 = new Student("中国龙" + i, "男", 18);
            mongoOperations.save(student1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗" + (endTime - startTime) + "ms");
    }

}
