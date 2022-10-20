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
	static Map<String, Contact> newcontacts;
	private static String city;
	static String state;

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

	// for creating multiple addressbook and adding contact
	Map<String, Contact> addMultipleAddressBook() {
		multipleAddBook = new HashMap<String, Contact>();
		for (int i = 1; i < 2; i++) {
			System.out.println("Please Confirm if you want to add multiple Address Book ,Enter Y/N ");
			Scanner sc = new Scanner(System.in);

			char choice = sc.next().charAt(0);
			if (choice == 'Y') {

				System.out.println("Enter the name of AddressBook");

				String addBookName = sc.next().toLowerCase();

				AddressBook addAddrBook = new AddressBook();
				System.out.println(addBookName);
				newcontacts = addAddrBook.createAddressBook(addBookName);

				multipleAddBook.putAll(newcontacts);
				// System.out.println(multipleAddBook);

				i = 0;
			} else
				System.out.println("Created AddressBook" + multipleAddBook);

		}
		return multipleAddBook;
	}

	public Map<String, Contact> createAddressBook(String addressBookName) {
		newcontacts = new HashMap<String, Contact>();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the No of contacts you want to create");
		int noOfContact = sc.nextInt();

		for (int i = 1; i <= noOfContact; i++) {
			AddressBook AddContact = new AddressBook();
			Contact contact = AddContact.contactDetails();
			// Check for duplicate contact as key
			boolean result = newcontacts.entrySet().stream().anyMatch(
					contactMap -> contactMap.getValue().getFirstName().contains(contact.getFirstName().toLowerCase()));
			if (result == true) {
				System.out.println("Contact already exists");

			} else
				newcontacts.put((contact.getFirstName().toLowerCase() + "." + addressBookName.toLowerCase()), contact);
		}

		newcontacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

		System.out.println("Please confirm if you want to delete Contact \n Enter Y/N");
		char delchoice = sc.next().charAt(0);
		AddressBook addBookobj = new AddressBook();
		if (delchoice == 'Y')
			newcontacts.remove(addBookobj.deleteContact());
		// System.out.println(newcontacts);

		System.out.println("Please confirm if you want to Update Contact Enter Y/N");
		char updatechoice = sc.next().charAt(0);

		if (updatechoice == 'Y') {
			// remove old contact and updating new key as first name
			String updateContact = addBookobj.updateContact();
			if (newcontacts.containsKey(updateContact.toLowerCase()))
				newcontacts.remove(updateContact);

			Contact updatecontact = contactDetails();
			newcontacts.put(updatecontact.getFirstName().toLowerCase(), updatecontact);

		}
		newcontacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(newcontacts);

		return newcontacts;

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

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("Welcome to Address Book");

		AddressBook adBook = new AddressBook();
		Map<String, Contact> addressBookSystem = adBook.addMultipleAddressBook();

		System.out.println("Enter city name for searching contacts by City");
		Scanner sc = new Scanner(System.in);
		city = sc.nextLine().toLowerCase();
		SearchPerson Searchedersons = new SearchPerson();
		Searchedersons.getPersonByCity(addressBookSystem, city);

		System.out.println("Enter State name for searching contacts by State");
		state = sc.nextLine().toLowerCase();
		Map<String, Contact> personsWithState = Searchedersons.getPersonByState(addressBookSystem, city, state);
		System.out.println(personsWithState);

		// UC11 SORTING MAP using person name alphabetically
		HashMap<String, Contact> temp = addressBookSystem.entrySet().stream()
				.sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println("Sorted addesbook by person name" + temp);

		// calling file Writer method
		FileIOAddBook.readWriteOperation(addressBookSystem);
	}
}
