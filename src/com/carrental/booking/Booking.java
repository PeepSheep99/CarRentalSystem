package com.carrental.booking;

public class Booking {

	private String bookingStartDate;
	private String bookingEndDate;
	private int customerId;			
	private int bookedCarId;
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Booking(String bookingStartDate, String bookingEndDate, int customerId, int bookedCarId) {
		super();
		this.bookingStartDate = bookingStartDate;
		this.bookingEndDate = bookingEndDate;
		this.customerId = customerId;
		this.bookedCarId = bookedCarId;
	}


	public int getBookedCarId() {
		return bookedCarId;
	}


	public void setBookedCarId(int bookedCarId) {
		this.bookedCarId = bookedCarId;
	}


	public String getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(String bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public String getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(String bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
