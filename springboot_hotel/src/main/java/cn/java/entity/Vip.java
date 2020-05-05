/**
 * Project Name:springboot_hotel
 * File Name:Vip.java
 * Package Name:cn.java.entity
 * Date:下午2:32:23
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 下午2:32:23 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public class Vip implements Serializable {
    private Long id;// vip信息的主键

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 5383375079360000915L;

    @NotNull(message = "*会员名格式错误")
    @Pattern(regexp = ".{2,20}", message = "*会员名格式错误")
    private String customerName;// 会员名

    @NotNull(message = "*性别格式错误")
    @Pattern(regexp = "[10]", message = "*性别格式错误")
    private String gender;// 会员性别 1男 0女

    @NotNull(message = "*身份证号格式错误")
    @Pattern(regexp = "\\d{17}[0-9X]", message = "*身份证号格式错误")
    private String idcard;// 身份证号

    @NotNull(message = "*手机号格式错误")
    @Pattern(regexp = "1[35789]\\d{9}", message = "*手机号格式错误")
    private String phone;// 手机号码

    private String vipNum;// 会员编号

    private String createDate;// 创建时间

    private Float vipRate = 0.95F;// 折扣

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Float getVipRate() {
        return vipRate;
    }

    public void setVipRate(Float vipRate) {
        this.vipRate = vipRate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "Vip [id=" + id + ", customerName=" + customerName + ", gender=" + gender + ", idcard=" + idcard
                + ", phone=" + phone + ", vipNum=" + vipNum + ", createDate=" + createDate + ", vipRate=" + vipRate
                + "]";
    }

}
