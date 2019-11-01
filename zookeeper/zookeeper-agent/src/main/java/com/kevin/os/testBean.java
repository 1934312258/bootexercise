package com.kevin.os;

import java.util.List;

/**
 * @author kevin
 * @date 2019-10-21 15:23
 * @description todo
 **/
public class testBean {
    private Boolean single;
    private List<OsBean> giftProductDatas;
    private String consumeChannel;
    private Boolean supportMultiple;
    private String couponTemplateCode;
    private String couponType;
    private String name;
    private Double discount;
    private Double threshold;

    public Boolean getSingle() {
        return single;
    }

    public void setSingle(Boolean single) {
        this.single = single;
    }

    public List<OsBean> getGiftProductDatas() {
        return giftProductDatas;
    }

    public void setGiftProductDatas(List<OsBean> giftProductDatas) {
        this.giftProductDatas = giftProductDatas;
    }

    public String getConsumeChannel() {
        return consumeChannel;
    }

    public void setConsumeChannel(String consumeChannel) {
        this.consumeChannel = consumeChannel;
    }

    public Boolean getSupportMultiple() {
        return supportMultiple;
    }

    public void setSupportMultiple(Boolean supportMultiple) {
        this.supportMultiple = supportMultiple;
    }

    public String getCouponTemplateCode() {
        return couponTemplateCode;
    }

    public void setCouponTemplateCode(String couponTemplateCode) {
        this.couponTemplateCode = couponTemplateCode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }
}
