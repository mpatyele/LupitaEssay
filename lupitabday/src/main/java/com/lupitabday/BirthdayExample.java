package com.lupitabday;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BirthdayExample {

  // this is a private and static hashmap to store the birthdays
  private static HashMap<String, String> birthdayMap = new HashMap<String, String>();

  // this code reads a JSON file
  public static JSONArray readJSONArrayFile(String fileName) {
    JSONParser jsonParser = new JSONParser();
    JSONArray birthdayArr = null;

    // Read JSON file
    try (FileReader reader = new FileReader(fileName)) {
      Object obj = jsonParser.parse(reader);
      birthdayArr = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayArr;
  }

  // Initialize the map from the JSON file
  public static void initializeMap(final String pathToFile) {
    JSONArray jsonData = readJSONArrayFile(pathToFile);

    // Loop over the list
    String birthday, name;
    JSONObject obj;
    for (Integer i = 0; i < jsonData.size(); i++) {
      // Parse the object and pull out the name and birthday
      obj = (JSONObject) jsonData.get(i);
      birthday = (String) obj.get("birthday");
      name = (String) obj.get("name");

      // Add the name and birthday into the hashmap
      birthdayMap.put(name, birthday);
    }
  }

  public static void main(final String[] args) {
    String pathToFile = "C:\\Users\\maria\\Documents\\GitHub\\LupitaEssay\\lupitabday\\src\\main\\java\\com\\lupitabday\\birthday.json";

    // Initialize the hash map
    initializeMap(pathToFile);

    // Read user input from keyboard
    System.out.println("Reading user input into a string");

    // Get user input
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name: ");
    String inputName = input.nextLine().trim();

    // Check for exact matches first
    if (birthdayMap.containsKey(inputName)) {
      System.out.println("name = " + inputName);
      System.out.println("The birthday is " + birthdayMap.get(inputName));
    } else {
      boolean found = false;
      for (Map.Entry<String, String> entry : birthdayMap.entrySet()) {
        if (entry.getKey().toLowerCase().contains(inputName.toLowerCase())) {
          System.out.println("name = " + entry.getKey());
          System.out.println("The birthday is " + entry.getValue());
          found = true;
        }
      }

      if (!found) {
        System.out.println("Sorry, no birthdays found for the name containing \"" + inputName + "\".");
      }
    }

    // Close the scanner
    input.close();
  }
}
