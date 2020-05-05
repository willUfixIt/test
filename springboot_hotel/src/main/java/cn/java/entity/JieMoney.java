/**
 * Project Name:springboot_hotel
 * File Name:JieMoney.java
 * Package Name:cn.java.entity
 * Date:下午3:14:29
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.entity;

import java.io.Serializable;

/**
 * Description:封装退房结算信息 QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午3:14:29 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public class JieMoney implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 4003220324248534513L;

    private Long roomId;// 房间ID

    private String customerName;// 客人名字

    private Float price;// 房间单价

    private Float yajin;// 押金

    private Float qita;// 其他消费

    private String date1;// 入住时间

    private String date2;// 退房时间

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getYajin() {
        return yajin;
    }

    public void setYajin(Float yajin) {
        this.yajin = yajin;
    }

    public Float getQita() {
        return qita;
    }

    public void setQita(Float qita) {
        this.qita = qita;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "JieMoney [roomId=" + roomId + ", customerName=" + customerName + ", price=" + price + ", yajin=" + yajin
                + ", qita=" + qita + ", date1=" + date1 + ", date2=" + date2 + "]";
    }

}
