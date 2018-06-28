package com.judya.judya;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hotel_UtilSorting {

	public static List<JSONObject> JSONArrayToList(JSONArray arrJson) {

		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < arrJson.length(); i++) {
			try {
				jsonValues.add(arrJson.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jsonValues;

	}

	public static JSONArray sortName(List<JSONObject> jsonValues,
                                     final String type) {

		// Name A-Z
		JSONArray sortedJsonArray = new JSONArray();

		Collections.sort(jsonValues, new Comparator<JSONObject>() {
			// You can change "Name" with "ID" if you want to sort by ID
			private static final String KEY_NAME = "name";

			@Override
			public int compare(JSONObject a, JSONObject b) {
				String valA = new String();
				String valB = new String();

				try {
					valA = (String) a.get(KEY_NAME);
					valB = (String) b.get(KEY_NAME);
				} catch (JSONException e) {
					// do something
				}

				if (type.equals("ASC")) {

					return valA.compareTo(valB);

				} else if (type.equals("DESC")) {
					return -valA.compareTo(valB);

				} else {

					return 0;

				}
			}
		});

		for (int i = 0; i < jsonValues.size(); i++) {
			sortedJsonArray.put(jsonValues.get(i));
		}

		return sortedJsonArray;

	}

	public static JSONArray sortInt(List<JSONObject> jsonValues, final String keyname, final String type) {

		// Prices l-h
		JSONArray sortedJsonArray = new JSONArray();

		Collections.sort(jsonValues, new Comparator<JSONObject>() {
			// You can change "Name" with "ID" if you want to sort by ID
			private final String KEY_NAME = keyname;

			@Override
			public int compare(JSONObject a, JSONObject b) {
				int valA = 0;

				int valB = 0;

				try {
					valA = a.getInt(KEY_NAME);
					valB = b.getInt(KEY_NAME);
				} catch (JSONException e) {
					// do something
				}

				
				if (type.equals("ASC")) {

					return valA - valB;

				} else if (type.equals("DESC")) {
					return -valA - valB;

				} else {

					return 0;

				}

			}
		});

		for (int i = 0; i < jsonValues.size(); i++) {
			sortedJsonArray.put(jsonValues.get(i));
		}

		return sortedJsonArray;

	}

 
	public static JSONArray sortTypeDouble(List<JSONObject> jsonValues, final String keyname, final String type) {

		JSONArray sortedJsonArray = new JSONArray();

		Collections.sort(jsonValues, new Comparator<JSONObject>() {
			// You can change "Name" with "ID" if you want to sort by ID
			private final String KEY_NAME = keyname;

			@Override
			public int compare(JSONObject a, JSONObject b) {
				Double valA = 0.0;

				Double valB = 0.0;

				try {
					valA = a.getDouble(KEY_NAME);
					valB = b.getDouble(KEY_NAME);
				} catch (JSONException e) {
					// do something
				}

				
				if (type.equals("ASC")) {

					return valA.compareTo(valB);

				} else if (type.equals("DESC")) {
					return -valA.compareTo(valB);

				} else {

					return 0;

				}
				
				

			}
		});

		for (int i = 0; i < jsonValues.size(); i++) {
			sortedJsonArray.put(jsonValues.get(i));
		}

		return sortedJsonArray;

	}
	
	
	
}
