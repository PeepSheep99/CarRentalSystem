package com.carrental.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carrental.booking.Booking;

public class BookingDao {

	public static boolean createBooking(Booking booking) {
		boolean flag = false;
		try {
			// Create connection
			Connection connect = ConnectionClass.create();

			// Query
			String query = "insert into booking values(?,?,?,?)";

			// PreparedStatement
			PreparedStatement pstm = connect.prepareStatement(query);

			// Set values of prepared Statement
			pstm.setString(1, booking.getBookingStartDate());
			pstm.setString(2, booking.getBookingEndDate());
			pstm.setInt(3, booking.getCustomerId());
			pstm.setInt(4, booking.getBookedCarId());

			// Execute
			pstm.executeUpdate();

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static boolean viewBookings(int customerId) {
		try {
			Connection connect = ConnectionClass.create();

			String query = "select b.customerId,c.carId,c.carModel,c.carCostPerDay,b.bookingStartDate,b.bookingEndDate from carInventory c inner join booking b on c.carId = b.bookedCarId where b.customerId = ?";

			PreparedStatement pStatement = connect.prepareStatement(query);
			pStatement.setInt(1, customerId);
			ResultSet res = pStatement.executeQuery();

			if (res.next() == false) {
				
				return false;
			}

			else {
				System.out.printf("%5s %7s %10s %10s %10s %10s", "customerId", "carId", "carModel", "carCostPerDay",
						"bookingStartDate", "bookingEndDate");
				System.out.println();
				do {

					int cId = res.getInt(1);
					int carId = res.getInt(2);
					String carModel = res.getString(3);
					int carCostPerDay = res.getInt(4);
					String bookingStartDate = res.getString(5);
					String bookingEndDate = res.getString(6);

					System.out.printf("%5d %10d %10s %10d %15s %17s", cId, carId, carModel, carCostPerDay,
							bookingStartDate, bookingEndDate);
					System.out.println();
				} while (res.next());
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteBooking(int bookedCarId, String bookingStartDate) {
		boolean flag = false;
		try {
			Connection connect = ConnectionClass.create();

			String query = "delete from booking where bookedCarId = ? and bookingStartDate = ?";

			PreparedStatement pStatement = connect.prepareStatement(query);
			pStatement.setInt(1, bookedCarId);
			pStatement.setString(2, bookingStartDate);
			pStatement.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static int getTotalCost(int customerId) {

		try {

			int curBookedCarId = 0, curCarPerDayCost = 0, curDateDiff = 0;
			Connection connect = ConnectionClass.create();

			String getBookedCarIdQuery = "select bookedCarId from booking where customerId = ?";
			PreparedStatement pStatement = connect.prepareStatement(getBookedCarIdQuery);
			pStatement.setInt(1, customerId);
			ResultSet bookedCarIdRes = pStatement.executeQuery();
			while (bookedCarIdRes.next()) {
				curBookedCarId = bookedCarIdRes.getInt(1);
				// System.out.println("CurBookedCarId ="+curBookedCarId);
			}

			String getCarCostPerDayQuery = "select carCostPerDay from carInventory where carId = ?";
			PreparedStatement pStatement2 = connect.prepareStatement(getCarCostPerDayQuery);
			pStatement2.setInt(1, curBookedCarId);
			ResultSet carCostPerDayRes = pStatement2.executeQuery();
			while (carCostPerDayRes.next()) {
				curCarPerDayCost = carCostPerDayRes.getInt(1);
				// System.out.println("curCarPerDayCost ="+curCarPerDayCost);
			}

			String getDateDiffQuery = "select dateDiff(bookingEndDate,bookingStartDate) from booking where customerId = ?";
			PreparedStatement pStatement3 = connect.prepareStatement(getDateDiffQuery);

			pStatement3.setInt(1, customerId);
			ResultSet dateDiffRes = pStatement3.executeQuery();
			while (dateDiffRes.next()) {
				curDateDiff = dateDiffRes.getInt(1);

			}

			return curDateDiff * curCarPerDayCost;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
