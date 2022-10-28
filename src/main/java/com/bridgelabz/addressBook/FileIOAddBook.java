package com.bridgelabz.addressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileIOAddBook {

	public static void readWriteOperation(String type, Contact contact) throws IOException, ParseException {

		String fileURL = "C:\\Users\\aashi\\eclipse-workspace\\addressBook\\src\\main\\resources\\AddressBook.txt";
		// Converts Hashmap to JSON As ObjectMapper is used, it writes JSON // string
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Contact> addressBookContact = new HashMap<>();
		addressBookContact.put(contact.getFirstName() + "." + type, contact);

		try {

			String AddressBookJson = mapper.writeValueAsString(addressBookContact);

			// Print JSON output
			System.out.println("JSON" + AddressBookJson);

			FileWriter fileWriterObj = new FileWriter(fileURL, true);

			fileWriterObj.write("\n" + AddressBookJson);

			fileWriterObj.close();
		}

		// Catching generic input output exceptions
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readJSONdata() throws IOException, ParseException {

		// public static void main(String[] args) throws IOException, ParseException {
		String fileURL = "C:\\Users\\aashi\\eclipse-workspace\\addressBook\\src\\main\\resources\\AddressBook.txt";

		// store in map after reading the data
		Map<String, String> map = new HashMap<>();

		try (Stream<String> lines = Files.lines(Paths.get(fileURL))) {
			lines.filter(line -> line.contains(":")).forEach(line -> {
				String[] keyValuePair = line.split(":", 2); // split the line into 2 parts
				String key = keyValuePair[0].trim();
				String value = keyValuePair[1].trim();
				System.out.println(key + " : " + value);
				map.put(key, value);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}