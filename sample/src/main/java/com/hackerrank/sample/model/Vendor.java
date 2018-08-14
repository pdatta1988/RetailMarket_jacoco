package com.hackerrank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendor")
public class Vendor {
	
	@Id
	@Column(name="vendorid")
	private int vendorId = 0;
	
	@Column(name="vendorname")
	private String vendorName = "";
	
	@Column(name="vendorcontactno")
	private long vendorContactNo = 0l;
	
	@Column(name="vendoremail")
	private String vendorEmail = "";
	
	@Column(name="vendorusername")
	private String vendorUsername = "";
	
	@Column(name="vendoraddress")
	private String vendorAddress = "";
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public long getVendorContactNo() {
		return vendorContactNo;
	}
	public void setVendorContactNo(long vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}
	public String getVendorUsername() {
		return vendorUsername;
	}
	public void setVendorUsername(String vendorUsername) {
		this.vendorUsername = vendorUsername;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vendor [vendorId=");
		builder.append(vendorId);
		builder.append(", vendorName=");
		builder.append(vendorName);
		builder.append(", vendorContactNo=");
		builder.append(vendorContactNo);
		builder.append(", vendorEmail=");
		builder.append(vendorEmail);
		builder.append(", vendorUsername=");
		builder.append(vendorUsername);
		builder.append(", vendorAddress=");
		builder.append(vendorAddress);
		builder.append(", getVendorId()=");
		builder.append(getVendorId());
		builder.append(", getVendorName()=");
		builder.append(getVendorName());
		builder.append(", getVendorContactNo()=");
		builder.append(getVendorContactNo());
		builder.append(", getVendorEmail()=");
		builder.append(getVendorEmail());
		builder.append(", getVendorUsername()=");
		builder.append(getVendorUsername());
		builder.append(", getVendorAddress()=");
		builder.append(getVendorAddress());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}