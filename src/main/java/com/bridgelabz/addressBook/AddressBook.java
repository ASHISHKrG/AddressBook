package com.bridgelabz.addressBook;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressBook {

	static Map<String, Contact> multipleAddBook;

	Map<String, Contact> addressBookSystem;

	// creating contact for addressBook
	public Contact contactDetails() {
		Contact contact = new Contact();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the FirstName");
		contact.setFirstName(sc.nextLine().toLowerCase());

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

	// for creating multiple addressbook and adding contact
	Map<String, Contact> addMultipleAddressBook() {

		multipleAddBook = new HashMap<String, Contact>();
		for (int i = 1; i < 2; i++) {
			System.out.println("Please Confirm if you want to add  Address Book ,Enter Y/N ");
			Scanner sc = new Scanner(System.in);

			char choice = sc.next().toLowerCase().charAt(0);
			if (choice == 'y') {

				System.out.println("Enter the Name/Type of AddressBook");

				String addBookName = sc.next().toLowerCase();

				AddressBook addBookObj = new AddressBook();

				System.out.println("Enter the No of contacts you want to create");
				int noOfContact = sc.nextInt();

				for (int j = 1; j <= noOfContact; j++) {

					Contact contact = addBookObj.contactDetails();
					// UC-> Check for duplicate contact as key
					boolean result = multipleAddBook.entrySet().stream()
							.anyMatch(contactMap -> contactMap.getValue().getFirstName().contains(
									contact.getFirstName().toLowerCase()) && contactMap.getKey().contains(addBookName));
					if (result == true) {
						System.out.println("Contact already exists");

					} else {

						DbConnection insertTODb = new DbConnection();
						insertTODb.insertContact(contact, addBookName);

						multipleAddBook.put((contact.getFirstName().toLowerCase() + "." + addBookName.toLowerCase()),
								contact);
						System.out.println("Contact added in Address Book");
					}
				}
				i = 0; // if not enter Y IN LINE 54
				multipleAddBook.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
			}
		}
		return multipleAddBook;
	}

	void addContactInAddressBook() {
		multipleAddBook = new HashMap<String, Contact>();
		System.out.println("Enter the Name/Type of AddressBook");
		Scanner sc = new Scanner(System.in);
		String addBookName = sc.next().toLowerCase();

		AddressBook addBookObj = new AddressBook();
		Contact contact = addBookObj.contactDetails();
		// Check for duplicate contact as key
		boolean result = multipleAddBook.entrySet().stream().anyMatch(
				contactMap -> contactMap.getValue().getFirstName().contains(contact.getFirstName().toLowerCase())); // &&
																													// contactMap.getKey().contains(addBookName));
		if (result == true) {
			System.out.println("Contact already exists");

		} else {

			DbConnection insertTODb = new DbConnection();
			insertTODb.insertContact(contact, addBookName);

			multipleAddBook.put((contact.getFirstName().toLowerCase() + "." + addBookName.toLowerCase()), contact);
		}
	}

	void deleteContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First Name of the Contact you want to Delete");
		String contactToRemove = sc.nextLine().toLowerCase();
		// deleting from database
		DbConnection dBConn = new DbConnection();
		dBConn.deleteContact(contactToRemove);
		multipleAddBook.remove(contactToRemove);
		multipleAddBook.keySet().removeIf(key -> key.startsWith(contactToRemove));

	}

	Map<String, Contact> updateContact(Map<String, Contact> addBookSystem) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Address Book name for updating Contact");
		String addBookName = sc.next().toLowerCase();
		System.out.println("Enter the First Name of the Contact you want to update");
		String updateContactName = sc.next().toLowerCase();

		// remove old contact and updating new key as first name.addressbook

		addBookSystem.keySet().removeIf(key -> key.startsWith(updateContactName + "." + addBookName));
		Contact contact = contactDetails();
		DbConnection dbConn = new DbConnection();
		dbConn.updateContactByFirstName(updateContactName, addBookName, contact);
		addBookSystem.put(contact.getFirstName().toLowerCase() + "." + addBookName.toLowerCase(), contact);
		System.out.println("Contact updated in " + addBookName);
		addBookSystem.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

		return addBookSystem;
	}

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("Welcome to Address Book");
		// Map<String, Contact> addressBookSystem = new HashMap<>();

		SearchPerson Search = new SearchPerson();
		Scanner sc = new Scanner(System.in);

		AddressBook addBookobj = new AddressBook();
		addBookobj.addMultipleAddressBook();

		for (int i = 1; i < 2; i++) {
			System.out.println(
					"Enter from below options\n 1. Create contact \n 2: Display Contacts\n 3: Edit Contact\n 4: Delete  contact\n "
							+ "5: Add Multiple contacts\n 6: Search Contacts By City\n 7: Serach Contacts By State\n 8: Close Address Book \n \"Enter your selection : ");

			int option = sc.nextInt(); // get the number as a single line
			i = 0;
			switch (option) {
			/*
			 * case 1: a.addContact(s); break; case 2: a.displayContact(); break;
			 */
			case 1:
				addBookobj.addContactInAddressBook();
				break;
			case 2:
				DbConnection dBcon = new DbConnection();
				dBcon.getContactsByType();
				break;
			case 3:
				addBookobj.updateContact(multipleAddBook);
				break;
			case 4:
				addBookobj.deleteContact();
				break;
			case 5:
				addBookobj.addMultipleAddressBook();
				break;
			case 6:
				Search.getPersonByCity(multipleAddBook);
				break;
			case 7:
				Search.getPersonByState(multipleAddBook);
				break;
			case 8:
				i = 2;
				break;
			}

		}
		// UC11 SORTING MAP using person name alphabetically
		HashMap<String, Contact> addressBookSystem = multipleAddBook.entrySet().stream()
				.sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println("Sorted addesbook by person name" + addressBookSystem);

		// calling file Writer method
		FileIOAddBook.readWriteOperation(multipleAddBook);

	}
}
