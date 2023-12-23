package org.example.model.RE;

import io.swagger.models.auth.In;

public class PriceDiffRE {
    protected Integer id;
    protected String tag;
    protected String goodsName;
    protected String platformName;
    protected String sellerName;
    protected double priceDiffNum;
    protected double maxPrice;
    protected double minPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getPriceDiffNum() {
        return priceDiffNum;
    }

    public void setPriceDiffNum(double priceDiffNum) {
        this.priceDiffNum = priceDiffNum;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
