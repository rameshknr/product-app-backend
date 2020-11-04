package com.ust.productapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements Comparable<Product>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	public String productName;
	public String productType;
	public Date expiryDate;
	public Double price;
	
	public Product() {
		super();
	}

	public Product(int id, String productName,String productType,Date expiryDate,Double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.productType = productType;
		this.expiryDate = expiryDate;
		this.price = price;
	}
	
	public int compareTo(Product o) {
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		} else {
			return 0;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productType=" + productType + ", expiryDate="
				+ expiryDate + ", price=" + price + "]";
	}
	

	
	
	
}
