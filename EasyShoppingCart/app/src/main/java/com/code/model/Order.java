package com.code.model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
	private int Order_Id;
	private Cart cart;
	private String address;
	private String card;
	private String status;
	private float price;
	private Date start_time;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int order_Id, Cart cart, String address,
			String card, String status, float price, Date start_time) {
		super();
		Order_Id = order_Id;
		this.cart = cart;
		this.address = address;
		this.card = card;
		this.status = status;
		this.price = price;
		this.start_time = start_time;
	}

	public int getOrder_Id() {
		return Order_Id;
	}

	public void setOrder_Id(int order_Id) {
		Order_Id = order_Id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}


}
