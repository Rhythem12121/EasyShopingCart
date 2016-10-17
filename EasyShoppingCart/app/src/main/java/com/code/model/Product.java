package com.code.model;

import java.io.Serializable;

public class Product implements Serializable {
	private int product_ID;
	private Categories category;
	private ProductDetail detail;
	private int quanlity;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(int product_ID, Categories category, ProductDetail detail,
			int quanlity) {
		this.product_ID = product_ID;
		this.category = category;
		this.detail = detail;
		this.quanlity = quanlity;
	}
	public int getProduct_ID() {
		return product_ID;
	}
	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public ProductDetail getDetail() {
		return detail;
	}
	public void setDetail(ProductDetail detail) {
		this.detail = detail;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}


}
