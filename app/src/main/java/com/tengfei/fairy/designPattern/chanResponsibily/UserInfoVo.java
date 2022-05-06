package com.tengfei.fairy.designPattern.chanResponsibily;

import java.io.Serializable;

/**
 * @ Description :登录成功后返回参数
 * @ Author 李腾飞
 * @ Time 2022/5/6   4:24 PM
 * @ Version :
 */
public class UserInfoVo  implements Serializable {
    private String CUST_NO;// 会员id
    private String CUST_RESERVE;// 预留信息
    private String isOften;//常用设备
    private String nineElement;//9要素

    public boolean isOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(boolean outOfDate) {
        this.outOfDate = outOfDate;
    }

    private boolean outOfDate;// 身份证过期

    public String getCUST_NO() {
        return CUST_NO;
    }

    public void setCUST_NO(String CUST_NO) {
        this.CUST_NO = CUST_NO;
    }

    public String getCUST_RESERVE() {
        return CUST_RESERVE;
    }

    public void setCUST_RESERVE(String CUST_RESERVE) {
        this.CUST_RESERVE = CUST_RESERVE;
    }

    public String getIsOften() {
        return isOften;
    }

    public void setIsOften(String isOften) {
        this.isOften = isOften;
    }

    public String getNineElement() {
        return nineElement;
    }

    public void setNineElement(String nineElement) {
        this.nineElement = nineElement;
    }


    public UserInfoVo(String cust_no, String cust_reserve, String isOften, String nineElement,boolean outOfDate) {
        CUST_NO = cust_no;
        CUST_RESERVE = cust_reserve;
        this.isOften = isOften;
        this.nineElement = nineElement;
        this.outOfDate=outOfDate;
    }
}
