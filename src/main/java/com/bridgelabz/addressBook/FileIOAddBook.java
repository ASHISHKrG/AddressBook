package com.bridgelabz.addressBook;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileIOAddBook {

	void fileWriterWithFileWriter(String fileURL, String dataToWrite) throws IOException {
		FileWriter fileWriterObj = new FileWriter(fileURL);
		System.out.println("FileIO" + dataToWrite);
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

		System.out.println("data" + addressBookArrayObj);
		// 5. iterate every json object
		addressBookArrayObj.forEach(addressBook -> {
			System.out.println("Firstname - " + ((JSONObject) addressBook).get("firstName"));
			System.out.println("City - " + ((JSONObject) addressBook).get("city"));
			System.out.println("State- " + ((JSONObject) addressBook).get("state"));
		});

	}
}
