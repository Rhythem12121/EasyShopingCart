package com.code.model;

/**
 * Created by dong on 12/21/2015.
 */
public class Article {
    private int id;
    private int customer_id;
    private String title;
    private String description;
    private float price;
    private String phone_number;
    private String address;
    private String link_image;

    public Article() {

    }

    public Article(int id, int customer_id, String title, String description, float price, String phone_number, String address) {
        this.id = id;
        this.customer_id = customer_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Article(int id, int customer_id, String title, String description, float price, String phone_number, String address, String link_image) {
        this.id = id;
        this.customer_id = customer_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.phone_number = phone_number;
        this.address = address;
        this.link_image = link_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }
}
