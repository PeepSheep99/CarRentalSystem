package com.carrental.manage;

import java.sql.DriverManager;

import java.sql.Connection;

public class ConnectionClass {

	static Connection connect;

	public static Connection create() {
		try {
			//load Drivers
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Create Connection
			String url = "jdbc:postgresql://localhost:5432/CarRentalSystem";
			String user = "postgres";
			String password = "admin";
			connect = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connect;
	}

}
