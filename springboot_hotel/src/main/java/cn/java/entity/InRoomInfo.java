package cn.java.entity;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InRoomInfo implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 9192307552618634118L;

    private String roomNum;// 房间号

    @NotNull(message = "*姓名格式错误")
    @Pattern(regexp = ".{2,20}", message = "*姓名格式错误")
    private String customerName;// 客人姓名

    @NotNull(message = "*性别数据错误")
    @Pattern(regexp = "[10]", message = "*性别数据错误")
    private String gender;// 性别：1男，0女

    @NotNull(message = "*VIP数据错误")
    @Pattern(regexp = "[10]", message = "*VIP数据错误")
    private String isVip;// 1VIP 0非vip

    @NotNull(message = "*身份证格式错误")
    @Pattern(regexp = "\\d{17}[0-1X]", message = "*身份证格式错误")
    private String idcard;// 身份证号

    @NotNull(message = "*手机号格式错误")
    @Pattern(regexp = "1[3589]\\d{9}", message = "*手机号证格式错误")
    private String phone;// 手机号码

    @NotNull(message = "*押金格式错误")
    @DecimalMin(value = "0", message = "*押金格式错误")
    @DecimalMax(value = "10000", message = "*押金格式错误")
    private Float money;// 押金，押金必须为正数

    private String createDate;// 入住时间

    private Long roomId;// 房间ID

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
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

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
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

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "InRoomInfo [roomNum=" + roomNum + ", customerName=" + customerName + ", gender=" + gender + ", isVip="
                + isVip + ", idcard=" + idcard + ", phone=" + phone + ", money=" + money + ", createDate=" + createDate
                + ", roomId=" + roomId + "]";
    }

}