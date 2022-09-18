package com.bridgelabz.addressBook;

import java.util.Scanner;

class Contact {

	String FirstName;
	String LastName;
	String Address;
	String City;
	String State;
	String PhoneNO;
	String Email;
	int ZipCode;

//	public Contact(String firstName, String address, String city, String state, String phoneNO, int zipCode,
//			String email) {
//		super();
//		FirstName = firstName;
//		Address = address;
//		City = city;
//		State = state;
//		PhoneNO = phoneNO;
//		ZipCode = zipCode;
//		Email = email;
//	}

	void showContacts() {
		System.out.println("FirstName : " + FirstName + "\nAddress: " + Address + " \nEmail : " + Email);
	}
}

public class AddressBook {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");

		// Contact contact= new
		// Contact("Ashish","Burlington","Lucknow","UP","9044855917",226001,"aashish7322@gmail.com");
		Contact contact = new Contact();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the FirstName");
		contact.FirstName = sc.nextLine();
		System.out.println("Enter the LastName");
		contact.LastName = sc.nextLine();
		System.out.println("Enter the Address");
		contact.Address = sc.nextLine();
		System.out.println("Enter the City");
		contact.City = sc.nextLine();
		System.out.println("Enter the State");
		contact.State = sc.nextLine();
		System.out.println("Enter the Phone NO");
		contact.PhoneNO = sc.nextLine();
		System.out.println("Enter the Email");
		contact.Email = sc.nextLine();
		System.out.println("Enter the ZipCode");
		contact.ZipCode = sc.nextInt();
		

		contact.showContacts();
		
	}
}