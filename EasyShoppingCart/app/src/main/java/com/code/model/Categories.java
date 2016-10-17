package com.code.model;

import java.io.Serializable;

public class Categories implements Serializable {
	private int categories_ID;
	private String title;
	private String description;
	
	public Categories() {
		// TODO Auto-generated constructor stub
	}

	public Categories(int categories_ID, String title, String description) {
		super();
		this.categories_ID = categories_ID;
		this.title = title;
		this.description = description;
	}

	public int getCategories_ID() {
		return categories_ID;
	}

	public void setCategories_ID(int categories_ID) {
		this.categories_ID = categories_ID;
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


}
