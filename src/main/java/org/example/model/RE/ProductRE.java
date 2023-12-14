package org.example.model.RE;

import org.example.model.entity.Goods;

import java.util.Date;

public class ProductRE {
    protected Integer id;

    protected String name;//商品名

    protected String location;//商品地址

    protected double price;
    protected double minPrice;
    protected String category;//商品种类

    protected String sellerName;
    protected String platformName;
    protected Date productionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }


    public ProductRE(Goods goods,String sellerName,String platformName){
        setId(goods.getId());
        setName(goods.getName());
        setCategory(goods.getCategory());
        setPrice(goods.getPrice());
        setMinPrice(goods.getMinPrice());
        setLocation(goods.getLocation());
        setProductionDate(goods.getProductionDate());
        setSellerName(sellerName);
        setPlatformName(platformName);
    }
    public ProductRE(Integer id, String name, String location, double price, double minPrice, String category, String sellerName, String platformName, Date productionDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.minPrice = minPrice;
        this.category = category;
        this.sellerName = sellerName;
        this.platformName = platformName;
        this.productionDate = productionDate;
    }
}
