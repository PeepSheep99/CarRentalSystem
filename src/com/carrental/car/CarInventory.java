package com.carrental.car;

public class CarInventory {

	private int carId; // primary key
	private String carType;
	private String carModel;
	private int carCount;
	private int carCostPerDay;

	public CarInventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarInventory(int carId, String carType, String carModel, int carCount, int carCostPerDay) {
		super();
		this.carId = carId;
		this.carType = carType;
		this.carModel = carModel;
		this.carCount = carCount;
		this.carCostPerDay = carCostPerDay;
	}

	public CarInventory(String carType, String carModel, int carCount, int carCostPerDay) {
		super();
		this.carType = carType;
		this.carModel = carModel;
		this.carCount = carCount;
		this.carCostPerDay = carCostPerDay;
	}

	public int getCarCostPerDay() {
		return carCostPerDay;
	}

	public void setCarCostPerDay(int carCostPerDay) {
		this.carCostPerDay = carCostPerDay;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

}
