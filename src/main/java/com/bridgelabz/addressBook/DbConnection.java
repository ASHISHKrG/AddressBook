package com.bridgelabz.addressBook;

import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/

public class DbConnection {
	
	PreparedStatement preparedStmt;
	// public static void main(String[] args) {
	public void insertContact(Contact contact, String addressBookName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook_servicce", "root",
					"ROOT");
			Statement stmt = con.createStatement();

			String query = " insert into addressbook (firstname, lastname, address, city, state, phoneno, email, zipcode, type) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, contact.getFirstName().toLowerCase());
			preparedStmt.setString(2, contact.getLastName());
			preparedStmt.setString(3, contact.getAddress());
			preparedStmt.setString(4, contact.getCity());
			preparedStmt.setString(5, contact.getState());
			preparedStmt.setString(6, contact.getPhoneNO());
			preparedStmt.setString(7, contact.getEmail());
			preparedStmt.setInt(8, contact.getZipCode());
			preparedStmt.setString(9, addressBookName);
			// Passing addressbook name as type in DB

			preparedStmt.execute();

			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteContact(String contactFirstName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook_servicce", "root",
					"ROOT");
			Statement stmt = con.createStatement();

			String query = "DELETE FROM addressbook WHERE firstname=? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, contactFirstName);

			preparedStmt.execute();

			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getContactsByType() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook_servicce", "root",
					"ROOT");
			Statement stmt = con.createStatement();

			String query = "select type ,count(*) from addressbook group by type ";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String Type = rs.getString("type");
				int count = rs.getInt("count(*)");

				System.out.format("%s - %s \n", Type, count);
			}
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();

			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateContactByFirstName(String contactFirstName,String addressBookName,Contact contact) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook_servicce", "root",
					"ROOT");
			Statement stmt = con.createStatement();

			String query = "DELETE FROM addressbook WHERE firstname=? ";

			 preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, contactFirstName);

			preparedStmt.execute();
			
			String queryToInsert = " insert into addressbook (firstname, lastname, address, city, state, phoneno, email, zipcode, type) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			 preparedStmt = con.prepareStatement(queryToInsert);
			preparedStmt.setString(1, contact.getFirstName().toLowerCase());
			preparedStmt.setString(2, contact.getLastName());
			preparedStmt.setString(3, contact.getAddress());
			preparedStmt.setString(4, contact.getCity());
			preparedStmt.setString(5, contact.getState());
			preparedStmt.setString(6, contact.getPhoneNO());
			preparedStmt.setString(7, contact.getEmail());
			preparedStmt.setInt(8, contact.getZipCode());
			preparedStmt.setString(9, addressBookName);
			// Passing addressbook name as type in DB

			preparedStmt.execute();


			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}