package com.bridgelabz.addressBook;

class Contacts {

	String FirstName;
	String LastName;
	String Address;
	String City;
	String State;
	String PhoneNO;
	int ZipCode;
	String Email;

	public Contacts(String firstName, String address, String city, String state, String phoneNO, int zipCode,
			String email) {
		super();
		FirstName = firstName;
		Address = address;
		City = city;
		State = state;
		PhoneNO = phoneNO;
		ZipCode = zipCode;
		Email = email;
	}

	void showContacts() {
		System.out.println("FirstName : " + FirstName + "\nAddress: " + Address + " \nEmail : " + Email);
	}
}

public class AddressBook {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");

		Contacts contact = new Contacts("Ashish", "Burlington", "Lucknow", "UP", "9044855917", 226001,
				"aashish7322@gmail.com");
		contact.showContacts();
	}
}
