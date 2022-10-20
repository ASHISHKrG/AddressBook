package com.bridgelabz.addressBook;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileIOAddBook {

	void fileWriterWithFileWriter(String fileURL, String dataToWrite) throws IOException {
		FileWriter fileWriterObj = new FileWriter(fileURL);
		//System.out.println("FileIO" + dataToWrite);
		fileWriterObj.write(dataToWrite);

		fileWriterObj.close();

	}

	public static void readJSONdata(String fileURL) throws IOException, ParseException {
		// 1 .FileReader Obj
		FileReader fileReaderObj = new FileReader(fileURL);
		// 2. creation of parser object
		JSONParser jsonParserObj = new JSONParser();
		// 3 . parsing filereader object using json parser and output is object passes
		// to
		// objRef of type Object
		Object objRef = jsonParserObj.parse(fileReaderObj);
		// 4creating refrence of array of json
		JSONArray addressBookArrayObj = new JSONArray();
		addressBookArrayObj.add(objRef);

		try {
			JSONObject jsonObject = (JSONObject) objRef;
			String name = (String) jsonObject.get("firstName");
			String city = (String) jsonObject.get("city");
			String state = (String) jsonObject.get("state");
			String lastName = (String) jsonObject.get("lastName");
			System.out.println("Name: " + name);
			System.out.println("city: " + city);
			System.out.println("State:" + state);
			System.out.println("LastName:" + lastName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readWriteOperation(Map<String, Contact> addressBookSystem) throws IOException, ParseException {

		FileIOAddBook fileWriterObj = new FileIOAddBook();

		String fileURL = "C:\\Users\\aashi\\eclipse-workspace\\addressBook\\src\\main\\resources\\AddressBook.txt";
		// Converts Hashmap to JSON As ObjectMapper is used, it writes JSON // string
		ObjectMapper mapper = new ObjectMapper();

		try {

			String AddressBookJson = mapper.writeValueAsString(addressBookSystem);

			// Print JSON output
			System.out.println("JSON" + AddressBookJson);
			fileWriterObj.fileWriterWithFileWriter(fileURL, AddressBookJson);
		}

		// Catching generic input output exceptions
		catch (IOException e) {
			e.printStackTrace();
		}

		FileIOAddBook.readJSONdata(fileURL);
	}
}