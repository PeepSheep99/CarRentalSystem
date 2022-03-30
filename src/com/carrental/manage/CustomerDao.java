package com.carrental.manage;

import java.sql.Connection;

import com.carrental.customer.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDao {

	public static boolean createCustomer(Customer customer) {

		boolean flag = false;
		try {
			// Create connection
			Connection connect = ConnectionClass.create();

			// Query
			String query = "insert into customer values(?,?,?,?,?)";

			// PreparedStatement
			PreparedStatement pstm = connect.prepareStatement(query);

			// Set values of prepared Statement
			pstm.setInt(1, customer.getCustomerId());
			pstm.setString(2, customer.getCustomerName());
			pstm.setString(3, customer.getCustomerDlNum());
			pstm.setString(4, customer.getCustomerPhoneNum());
			pstm.setString(5, customer.getCustomerAddress());

			// Execute
			pstm.executeUpdate();

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

}
