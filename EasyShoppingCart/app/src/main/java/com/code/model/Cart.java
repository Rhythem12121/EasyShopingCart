package com.code.model;

import java.io.Serializable;
import java.sql.Date;

public class Cart implements Serializable {
	private int cart_ID;
	private Customer customer;
	private String description;
	private Date create_time;
	private Date update_time;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public Cart(int cart_ID, String description, Date create_time,
			Date update_time) {
		super();
		this.cart_ID = cart_ID;
		this.description = description;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	
	public Cart(int cart_ID, Customer customer, String description,
			Date create_time, Date update_time) {
		super();
		this.cart_ID = cart_ID;
		this.customer = customer;
		this.description = description;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getCart_ID() {
		return cart_ID;
	}
	public void setCart_ID(int cart_ID) {
		this.cart_ID = cart_ID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
