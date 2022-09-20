package com.bridgelabz.addressBook;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Contact {

	String FirstName;
	String LastName;
	String Address;
	String City;
	String State;
	String PhoneNO;
	String Email;
	int ZipCode;

	public String toString() {
		return "First Name: " + FirstName + " LastName " + LastName + " Address." + Address;
	}

	public String getFirstName() {
		return FirstName;
	}

	void contactDetails() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the FirstName");
		FirstName = sc.nextLine();

		System.out.println("Enter the LastName");
		LastName = sc.nextLine();
		System.out.println("Enter the Address");
		Address = sc.nextLine();
		System.out.println("Enter the City");
		City = sc.nextLine();
		System.out.println("Enter the State");
		State = sc.nextLine();
		System.out.println("Enter the Phone NO");
		PhoneNO = sc.nextLine();
		System.out.println("Enter the Email");
		Email = sc.nextLine();
		System.out.println("Enter the ZipCode");
		ZipCode = sc.nextInt();

	}

	String deleteContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First Name of the Contact you want to Delete");
		String removeContact = sc.nextLine();
		return removeContact;
	}

	String updateContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First Name of the Contact you want to update");
		String updateContact = sc.nextLine();
		return updateContact;
	}
}

public class AddressBook {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");
		Contact contact = new Contact();
		Map<String, Contact> allContacts = new HashMap<String, Contact>();

		/* Add Contact using Constructor */
		// Contact contact= new
		// Contact("Ashish","Burlington","Lucknow","UP","9044855917",226001,"aashish7322@gmail.com");

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the No of contacts you want to create");
		int noOfContact = sc.nextInt();

		for (int i = 1; i <= noOfContact; i++) {
			contact = new Contact();
			contact.contactDetails();
			allContacts.put(contact.getFirstName(), contact);
		}

		allContacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(allContacts);

		String deleteContact = contact.deleteContact();
		allContacts.remove(deleteContact);
		System.out.println(allContacts);

		String updateContact = contact.updateContact();
		contact.contactDetails();
		allContacts.put(updateContact.toString(), contact);
		allContacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(allContacts);

		/*
		 * for (int i = 0; i < allContacts.size(); i++) { ArrayList<Object> data = new
		 * ArrayList<Object>(allContacts.keySet()); Object obj = data.get(i);
		 * System.out.println("AddressBook" + i + " :" + obj + " Contact : " +
		 * allContacts.get(obj)); }
		 */
		/*
		 * System.out.println("Enter the First Name of the Contact you want to update");
		 * String updateContact = sc.nextLine(); contact.contactDetails();
		 * allContacts.put(updateContact.toString(), contact); allContacts.forEach((k,
		 * v) -> System.out.println("Key = " + k + ", Value = " + v));
		 * //System.out.println(allContacts);
		 * 
		 * addressBook.put(contact.getFirstName(), allContacts); addressBook.forEach((k,
		 * v) -> System.out.println("Key = " + k + ", Value = " + v)); //
		 * System.out.println(allContacts); System.out.println(addressBook);
		 * 
		 * for (int i = 1; i <= allContacts.size(); i++) { ArrayList<Object> data = new
		 * ArrayList<Object>(allContacts.keySet()); Object obj = data.get(i);
		 * System.out.println("AddressBook" + i + " :" + obj + " Contact : " +
		 * allContacts.get(obj)); }
		 */

	}
}