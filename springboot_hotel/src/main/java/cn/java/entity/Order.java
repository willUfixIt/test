/**
 * Project Name:springboot_hotel
 * File Name:Order.java
 * Package Name:cn.java.entity
 * Date:上午10:13:16
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.entity;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午10:13:16 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public class Order implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -8121190911172685615L;

    private String orderNum;// 订单编号

    @NotNull(message = "*名字格式错误")
    @Pattern(regexp = ".{2,20}", message = "*名字格式错误")
    private String customerName;// 客人姓名

    @NotNull(message = "*身份证号格式错误")
    @Pattern(regexp = "\\d{17}[0-9X]", message = "*身份证号格式错误")
    private String idcard;// 身份证号

    @NotNull(message = "*手机号格式错误")
    @Pattern(regexp = "1[35789]\\d{9}", message = "*手机号格式错误")
    private String phone;// 手机号码

    @NotNull(message = "*金额格式错误")
    @DecimalMin(value = "0", message = "*金额格式错误")
    @DecimalMax(value = "100000000", message = "*金额格式错误")
    private Float money;// 消费金额

    @NotNull(message = "*结算状态数据错误")
    @Pattern(regexp = "[10]", message = "*结算状态数据错误")
    private String orderStatus;// 结算状态 1已经结算、0代表未结算

    private Long roomId;// 房间的主键

    private String createDate;// 订单创建时间

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Order [orderNum=" + orderNum + ", customerName=" + customerName + ", idcard=" + idcard + ", phone="
                + phone + ", money=" + money + ", orderStatus=" + orderStatus + ", roomId=" + roomId + ", createDate="
                + createDate + "]";
    }

}
