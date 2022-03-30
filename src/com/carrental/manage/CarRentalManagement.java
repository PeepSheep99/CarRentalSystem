package com.carrental.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.carrental.booking.Booking;
import com.carrental.car.CarInventory;
import com.carrental.customer.Customer;

public class CarRentalManagement {

	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println("\n********* Welcome to Car Rental System *********\n");

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);

		while (true) {
			System.out.println(
					"\nEnter your choice\n1: Book a car\n2: View available cars\n3: View your bookings\n4: Add a new car to inventory\n5: Return a car\n6: Exit");
			int choice = Integer.parseInt(br.readLine());

			switch (choice) {
			case 1:
				System.out.println("\n1:New Customer\n2:Existing Customer");
				int typeOfCustomer = Integer.parseInt(br.readLine());
				switch (typeOfCustomer) {
				case 1:
					// take in customer data
					int cId;
					String cName, cDlNum, cPhoneNum, cAddress;
					System.out.println("Enter the following details\nUnique CustomerId: ");
					cId = Integer.parseInt(br.readLine());
					System.out.println("\nName: ");
					cName = br.readLine();
					System.out.println("\nDL number: ");
					cDlNum = br.readLine();
					System.out.println("\nPhone Number: ");
					cPhoneNum = br.readLine();
					System.out.println("\nAddress: ");
					cAddress = br.readLine();

					// Create Customer object
					Customer newCustomer = new Customer(cId, cName, cDlNum, cPhoneNum, cAddress);

					// show the cars available
					CarDao.viewCarDetails();

					// Take the carId which the user wants to book
					System.out.println("Enter the carId you want to book:");
					int bookCarId = Integer.parseInt(br.readLine());

					// Check car count
					boolean checkCarCount = CarDao.checkCarCount(bookCarId);
					if (!checkCarCount) {
						System.out.println(
								"\nSorry! No cars are available for the particular carId\nTry booking another one.");
						break;
					}
					// Get booking dates
					System.out.println("Enter Booking Details:\nBooking From Date (YYYY-MM-DD) :");
					String bookingStartDate = br.readLine();
					System.out.println("Booking Till (YYYY-MM-DD) :");
					String bookingTillDate = br.readLine();

					boolean customerCreateStatus = CustomerDao.createCustomer(newCustomer);
					if (customerCreateStatus) {

						Booking newBooking = new Booking(bookingStartDate, bookingTillDate, cId, bookCarId);

						boolean bookingCreateStatus = BookingDao.createBooking(newBooking);
						if (bookingCreateStatus) {
							CarDao.decrementCarCount(bookCarId); // Count of car decremented by one
							System.out.println("Booking done sucessfully");
							int totalCost = BookingDao.getTotalCost(cId);
							System.out.println("\nAmount to be paid while returning car: " + totalCost);
						}
					}
					break;
				case 2:
					// Existing customer
					// show the cars available
					CarDao.viewCarDetails();

					// Take the carId which the user wants to book
					System.out.println("Enter the carId you want to book:");
					bookCarId = Integer.parseInt(br.readLine());

					// Check car count
					checkCarCount = CarDao.checkCarCount(bookCarId);
					if (!checkCarCount) {
						System.out.println(
								"\nSorry! No cars are available for the particular carId\nTry booking another one.");
						break;
					}
					// Get CustomerId and booking dates
					System.out.println("Enter your unique customer Id: ");
					int custId = Integer.parseInt(br.readLine());
					System.out.println("Enter Booking Details:\nBooking From Date (YYYY-MM-DD) :");
					bookingStartDate = br.readLine();
					System.out.println("Booking Till (YYYY-MM-DD) :");
					bookingTillDate = br.readLine();

					// create a new booking
					Booking newBooking = new Booking(bookingStartDate, bookingTillDate, custId, bookCarId);

					boolean bookingCreateStatus = BookingDao.createBooking(newBooking);
					if (bookingCreateStatus) {
						CarDao.decrementCarCount(bookCarId); // Count of car decremented by one
						System.out.println("\nBooking done sucessfully\n");
						int totalCost = BookingDao.getTotalCost(custId);
						System.out.println("\nAmount to be paid while returning car: " + totalCost);
					}
					break;
				}

				break;

			case 2:
				// Display car Inventory
				CarDao.viewCarDetails();
				break;
			case 3:
				// Access Booking table by taking customerId and display bookings
				System.out.println("Enter the customer Id to show bookings: ");
				int cId = Integer.parseInt(br.readLine());
				// Inner join on carInventory and booking
				BookingDao.viewBookings(cId);
				break;

			case 4:
				// Take in details of car and add car to inventory
				System.out.println("Enter the following details to add a car to inventory");
				System.out.println("Car Type\neg: SUV,Sedan,HatchBack,MUV,Convetible");
				String carType = br.readLine();
				System.out.println("Car Count:");
				int carCount = Integer.parseInt(br.readLine());
				if (carCount <= 0) {
					System.out.println("Car count value entered is invalid");
					break;
				}
				System.out.println("Car Model:");
				String carModel = br.readLine();
				System.out.print("Car cost per day:");
				int carCostPerDay = Integer.parseInt(br.readLine());

				CarInventory newCar = new CarInventory(carType, carModel, carCount, carCostPerDay);

				boolean result = CarDao.addCar(newCar);
				if (result) {
					System.out.println("New car added successfully\n");
				} else {
					System.out.println("Something went wrong :(");
				}
				break;
			case 5:
				// take input of customerId
				System.out.println("\nEnter you customer Id to view your bookings: ");
				int deleteCustomerId = Integer.parseInt(br.readLine());

				boolean getDeleteStatus = BookingDao.viewBookings(deleteCustomerId);
				if(!getDeleteStatus) {
					System.out.println("\nSorry no bookings found for the customerId\nTry booking a car.\n");
					break;
				}

				// take the input of carId to return
				System.out.println("\nEnter the carId you want to return: ");
				int carId = Integer.parseInt(br.readLine());
				System.out.println("\nEnter the bookingStartDate for the car you want to return: ");
				String getBookingStartDate = br.readLine();
				// display returned successful message and add count to particular car
				boolean deleteBookingOperation = BookingDao.deleteBooking(carId,getBookingStartDate);
				if (deleteBookingOperation) {
					CarDao.returnCar(carId);
					System.out.println("\nCar returned successfully");
					int totalCost = BookingDao.getTotalCost(deleteCustomerId);
					System.out.println("\nTotal Bill = " + totalCost);
				}
				break;
			case 6:
				System.out.println("Thank you for using our car rental system\nSee you again :)");
				try {
					ConnectionClass.connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.exit(0);
			default:
				System.out.println("Invalid choice\nRetry");
			}
		}

	}

}
