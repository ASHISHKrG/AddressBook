package com.bridgelabz.addressBook;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AddressBook {

	Map<String, Map<String, Contact>> multipleAddBook;

	// creating contact for addressBook
	public Contact contactDetails() {
		Contact contact = new Contact();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the FirstName");
		contact.setFirstName(sc.nextLine());

		System.out.println("Enter the LastName");
		contact.setLastName(sc.nextLine());
		System.out.println("Enter the Address");
		contact.setAddress(sc.nextLine());
		System.out.println("Enter the City");
		contact.setCity(sc.nextLine());
		System.out.println("Enter the State");
		contact.setState(sc.nextLine());
		System.out.println("Enter the Phone NO");
		contact.setPhoneNO(sc.nextLine());
		System.out.println("Enter the Email");
		contact.setEmail(sc.nextLine());
		System.out.println("Enter the ZipCode");
		contact.setZipCode(sc.nextInt());

		return contact;
	}

	public Map<String, Contact> createAddressBook() {
		Map<String, Contact> allContacts = new HashMap<String, Contact>();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the No of contacts you want to create");
		int noOfContact = sc.nextInt();

		for (int i = 1; i <= noOfContact; i++) {
			AddressBook AddContact = new AddressBook();
			Contact contact = AddContact.contactDetails();
			// Check for duplicate contact as key
			if (allContacts.containsKey(contact.getFirstName().toLowerCase())) {
				System.out.println("Contact already exists");

			} else
				allContacts.put(contact.getFirstName().toLowerCase(), contact);
		}

		allContacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

		System.out.println("Please confirm if you want to delete Contact \n Enter Y/N");
		char delchoice = sc.next().charAt(0);
		AddressBook addBookobj = new AddressBook();
		if (delchoice == 'Y')
			allContacts.remove(addBookobj.deleteContact());
		System.out.println(allContacts);

		System.out.println("Please confirm if you want to Update Contact Enter Y/N");
		char updatechoice = sc.next().charAt(0);

		if (updatechoice == 'Y') {
			// remove old contact and updating new key as first name
			String updateContact = addBookobj.updateContact();
			if (allContacts.containsKey(updateContact.toLowerCase()))
				allContacts.remove(updateContact);

			Contact updatecontact = contactDetails();
			allContacts.put(updatecontact.getFirstName().toLowerCase(), updatecontact);

		}
		allContacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(allContacts);

		return allContacts;

	}

	// for creating multiple addressbook and adding contact
	void addMultipleAddressBook() {
		multipleAddBook = new HashMap<String, Map<String, Contact>>();
		for (int i = 1; i < 2; i++) {
			System.out.println("Please Confirm if you want to add multiple Address Book ,Enter Y/N ");
			Scanner sc = new Scanner(System.in);

			char choice = sc.next().charAt(0);
			if (choice == 'Y') {

				System.out.println("Enter the name of AddressBook");

				String addBookName = sc.next().toLowerCase();

				AddressBook addAddBook = new AddressBook();
				System.out.println(addBookName);
				Map<String, Contact> newcontacts = addAddBook.createAddressBook();

				multipleAddBook.put(addBookName, newcontacts);
				System.out.println(multipleAddBook);
				i = 0;
			} else
				System.out.println("Created AddressBook" + multipleAddBook);

		}

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

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");

		AddressBook adBook = new AddressBook();
		adBook.addMultipleAddressBook();

	}

}
