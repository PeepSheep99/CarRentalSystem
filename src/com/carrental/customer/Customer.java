package com.carrental.customer;

public class Customer {

	private int customerId; // Primary Key
	private String customerName;
	private String customerDlNum;
	private String customerPhoneNum;
	private String customerAddress;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, String customerDlNum, String customerPhoneNum,
			String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerDlNum = customerDlNum;
		this.customerPhoneNum = customerPhoneNum;
		this.customerAddress = customerAddress;

	}

	public Customer(String customerName, String customerDlNum, String customerPhoneNum, String customerAddress) {
		super();
		this.customerName = customerName;
		this.customerDlNum = customerDlNum;
		this.customerPhoneNum = customerPhoneNum;
		this.customerAddress = customerAddress;

	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerDlNum() {
		return customerDlNum;
	}

	public void setCustomerDlNum(String customerDlNum) {
		this.customerDlNum = customerDlNum;
	}

	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}

	public void setCustomerPhoneNum(String customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
	}

}
