package com.hackerrank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory {
	
	@Id
	@Column(name="skuid")
	private int skuId = 0;
	
	@Column(name="product_name")
	private String productName = "";
	
	@Column(name="product_label")
	private String productLabel = "";
	
	@Column(name="inventoryonhand")
	private int inventoryOnHand = 0;
	
	@Column(name="minqtyreq")
	private int minQtyReq = 0;
	
	@Column(name="price")
	private double price = 0.00;
	
	public int getSkuId() {
		return skuId;
	}
	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}
	public int getInventoryOnHand() {
		return inventoryOnHand;
	}
	public void setInventoryOnHand(int inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}
	public int getMinQtyReq() {
		return minQtyReq;
	}
	public void setMinQtyReq(int minQtyReq) {
		this.minQtyReq = minQtyReq;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory [skuId=");
		builder.append(skuId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", productLabel=");
		builder.append(productLabel);
		builder.append(", inventoryOnHand=");
		builder.append(inventoryOnHand);
		builder.append(", minQtyReq=");
		builder.append(minQtyReq);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
}
