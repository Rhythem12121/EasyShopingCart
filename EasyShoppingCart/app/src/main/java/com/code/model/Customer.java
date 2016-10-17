package com.code.model;

import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {
    private int cumtomer_ID;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String address;
    private Date dateofbirth;
    private String gender;

    public Customer() {
        // TODO Auto-generated constructor stub
    }

    public Customer(int cumtomer_ID, String fullname, String username, String address,
                    String password, String email, Date dateofbirth, String gender) {
        super();
        this.cumtomer_ID = cumtomer_ID;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.address = address;
    }

    public int getCumtomer_ID() {
        return cumtomer_ID;
    }

    public void setCumtomer_ID(int cumtomer_ID) {
        this.cumtomer_ID = cumtomer_ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
