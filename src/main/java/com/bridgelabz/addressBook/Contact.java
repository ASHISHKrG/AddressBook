package com.bridgelabz.addressBook;

import java.util.Scanner;

class Contact {

	private String FirstName;
	private String LastName;
	private String Address;
	private String City;
	private String State;
	private String PhoneNO;
	private String Email;
	private int ZipCode;

	public String toString() {
		return "First Name: " + FirstName + " LastName " + LastName + " Address:" + Address + " City:" + City
				+ " State:" + State;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getPhoneNO() {
		return PhoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		PhoneNO = phoneNO;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getZipCode() {
		return ZipCode;
	}

	public void setZipCode(int zipCode) {
		ZipCode = zipCode;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

}