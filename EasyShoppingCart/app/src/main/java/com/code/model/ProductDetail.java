package com.code.model;

import java.io.Serializable;

public class ProductDetail implements Serializable {
    private int productdetail_ID;
    private String title;
    private String company;
    private float price;
    private String description;
    private String infomation;
    private String comment;
    private String link_image;

    public ProductDetail() {
        // TODO Auto-generated constructor stub
    }

    public ProductDetail(int productdetail_ID, String title, String company, float price, String description, String infomation, String comment, String link_image) {
        this.productdetail_ID = productdetail_ID;
        this.title = title;
        this.company = company;
        this.price = price;
        this.description = description;
        this.infomation = infomation;
        this.comment = comment;
        this.link_image = link_image;
    }

    public int getProductdetail_ID() {
        return productdetail_ID;
    }

    public void setProductdetail_ID(int productdetail_ID) {
        this.productdetail_ID = productdetail_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
