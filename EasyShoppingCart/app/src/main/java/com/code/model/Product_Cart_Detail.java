package com.code.model;

import java.io.Serializable;

public class Product_Cart_Detail implements Serializable {
	private int productcart_detail_ID;
	private Cart cart;
	private Product product;
	private int quanlity;
	private float price;
	public Product_Cart_Detail() {
		// TODO Auto-generated constructor stub
	}
	public Product_Cart_Detail(int productcart_detail_ID, Cart cart,
			Product product, int quanlity, float price) {
		super();
		this.productcart_detail_ID = productcart_detail_ID;
		this.cart = cart;
		this.product = product;
		this.quanlity = quanlity;
		this.price = price;
	}
	public int getProductcart_detail_ID() {
		return productcart_detail_ID;
	}
	public void setProductcart_detail_ID(int productcart_detail_ID) {
		this.productcart_detail_ID = productcart_detail_ID;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


}
