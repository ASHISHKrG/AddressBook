package com.bridgelabz.addressBook;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchPerson {

	Scanner sc = new Scanner(System.in);

//Search person by city
	Map<String, Contact> getPersonByCity(Map<String, Contact> addressBookSystem) {
		Map<String, Contact> city_Map = new HashMap<String, Contact>();

		System.out.println("Enter city name for searching contacts by City");

		String city = sc.next().toLowerCase();

		for (Map.Entry<String, Contact> entry : addressBookSystem.entrySet()) {

			// using put method to copy one Map to Other
			city_Map.put(entry.getValue().getCity().toLowerCase() + entry.getValue().getFirstName(), entry.getValue());

		}
		// uc12 sorting BY city name

		HashMap<String, Contact> sortedByCity = city_Map.entrySet().stream()
				.sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println("Sorted by city name" + sortedByCity);

		// UC-9 Removing contacts with different city
		city_Map.keySet().removeIf(key -> !key.startsWith(city));
		System.out.println(city_Map);

		// UC10 Stream for filtering contact with city
		long noOfContactsWithCity = addressBookSystem.entrySet().stream()
				.filter(contactMap -> contactMap.getValue().getCity().toLowerCase().equalsIgnoreCase(city)).count();

		System.out.println("No of Contacts with city " + city + " : " + noOfContactsWithCity);

		return city_Map;
	}

	Map<String, Contact> getPersonByState(Map<String, Contact> addressBookSystem) {
		Map<String, Contact> state_Map = new HashMap<>();
		System.out.println("Enter State name for searching contacts by State");
		String state = sc.next().toLowerCase();
		for (Map.Entry<String, Contact> entry : addressBookSystem.entrySet()) {

			// using put method to copy one Map to Other
			state_Map.put(entry.getValue().getState().toLowerCase() + entry.getValue().getFirstName(),
					entry.getValue());

		}

		state_Map.keySet().removeIf(key -> !key.startsWith(state));

		System.out.println(state_Map);

		System.out.println("Enter city name for searching");
		String city = sc.next();
		// filter contact using city and state
		HashMap<String, Contact> searchByCityAndState = addressBookSystem.entrySet().stream()
				.filter(contactMap -> contactMap.getValue().getCity().toLowerCase().equalsIgnoreCase(city)
						&& contactMap.getValue().getState().toLowerCase().equalsIgnoreCase(state))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println(searchByCityAndState);

		return state_Map;
	}

}
