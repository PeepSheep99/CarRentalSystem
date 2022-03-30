package com.carrental.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.carrental.car.CarInventory;

public class CarDao {

	public static void viewCarDetails() {

		try {
			// Create Connection
			Connection connect = ConnectionClass.create();

			// Query
			String query = "Select * from carInventory";

			// Execute query
			Statement statement = connect.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			// Print all details
			System.out.printf("%5s %10s %10s %20s %10s", "carId", "carType", "carCount", "carModel", "carCostPerDay");
			System.out.println();
			while (resultSet.next()) {

				int carId = resultSet.getInt(1);
				String carType = resultSet.getString("carType");
				int carCount = resultSet.getInt("carCount");
				String carModel = resultSet.getString("carModel");
				String carCostPerDay = resultSet.getString("carCostPerDay");

				System.out.format("%5d %10s %10s %20s %10s", carId, carType, carCount, carModel, carCostPerDay);
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean decrementCarCount(int bookCarId) {
		boolean flag = false;
		try {
			// Create Connection
			Connection connect = ConnectionClass.create();

			// Check for carCount = 0
			String checkQuery = "select carCount from carInventory where carId = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(checkQuery);
			preparedStatement.setInt(1, bookCarId);

			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int carCount = result.getInt("carCount");
				if (carCount == 0) {
					flag = false;
					break;
				}
			}

			// Query
			String query = "Update carInventory set carCount = carCount - 1 where carId = ? and carCount > 0";

			// Prepared Statement
			PreparedStatement pstm = connect.prepareStatement(query);

			// Set values of prepared statement
			pstm.setInt(1, bookCarId);

			// Execute Query
			pstm.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static void incrementCarCount(int bookCarId) {

		try {
			// Create Connection
			Connection connect = ConnectionClass.create();

			// Query
			String query = "Update carInventory set carCount = carCount + 1 where carId = ?";

			// Prepared Statement
			PreparedStatement pstm = connect.prepareStatement(query);

			// Set values of prepared statement
			pstm.setInt(1, bookCarId);

			// Execute Query
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean addCar(CarInventory carInventory) {
		boolean flag = false;
		try {
			// Create Connection
			Connection connect = ConnectionClass.create();

			// Query
			String query = "Insert into carInventory(carType,carCount,carModel,carCostPerDay) values(?,?,?,?)";

			// Prepared Statement
			PreparedStatement pstm = connect.prepareStatement(query);

			// Set values of prepared statement
			pstm.setString(1, carInventory.getCarType());
			pstm.setInt(2, carInventory.getCarCount());
			pstm.setString(3, carInventory.getCarModel());
			pstm.setInt(4, carInventory.getCarCostPerDay());

			// Execute Query
			pstm.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static void returnCar(int carId) {

		try {
			CarDao.incrementCarCount(carId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean checkCarCount(int carId) {
		boolean flag = false;
		try {
			// Create Connection
			Connection connect = ConnectionClass.create();

			// Check for carCount = 0
			String checkQuery = "select carCount from carInventory where carId = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(checkQuery);
			preparedStatement.setInt(1, carId);

			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int carCount = result.getInt(1);

				if (carCount == 0) {
					flag = false;
					break;
				}
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
