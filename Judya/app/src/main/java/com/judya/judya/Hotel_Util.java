package com.judya.judya;

import android.content.Context;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hotel_Util {

	public static int getStringIdentifier(Context context, String name) {
		return context.getResources().getIdentifier(name, "string",context.getPackageName());
	}

	public static int getDrawableImage(Context context, String name) {
		return context.getResources().getIdentifier(name, "drawable",context.getPackageName());
	}

	public static String hashPassword(String password) {

		byte[] data = null;
		try {
			data = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base64 = Base64.encodeToString(data, Base64.DEFAULT);

		return base64;

	}

	public static String md5(String s) {

		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);

		for (byte b : hash) {
			int i = (b & 0xFF);
			if (i < 0x10)
				hex.append('0');
			hex.append(Integer.toHexString(i));
		}

		return hex.toString();

	}


	





	public static int getSelecteedItems(String name,
			ArrayList<HashMap<String, Object>> arr) {

		int x = 0;

		for (int i = 0; i < arr.size(); i++) {

			HashMap<String, Object> map = arr.get(i);
			String tmpname = map.get("Name").toString();
			if (name.equals(tmpname)) {

				x = i;
				break;

			}

		}

		return x;

	}

	public static String checkNull(String text, String replaceText) {

		if (text == null || text.equals("null") || text.equals("<null>")) {
			text = replaceText;
		}
		return text;

	}

	public static String ShowDateBirthday(String text) {

		String[] separated = text.split("-");

		String y = separated[0];
		String m = separated[1];
		String d = separated[2];

		String full = d + "/" + m + "/" + y;
		return full;

	}



	public static String stringByReplacingOccurrencesOfString(String original, String findText, String repaceby) {

		original = original.replace(findText, repaceby);

		return original;

	}



	public static Boolean CompareCheckHave(JSONArray list, JSONObject data,
                                           String key) {



		if (list == null || list.length() == 0) {

			return false;

		} else {

			Boolean check = false;

			for (int i = 0; i < list.length(); i++) {
				JSONObject objTmp = new JSONObject();

				try {
					objTmp = list.getJSONObject(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					if (data.getString(key).equals(objTmp.getString(key))) {
						check = true;
						break;
					} else {
						check = false;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (check) {

				return true;

			} else {

				return false;

			}

		}

	}

	public static String ConvertBoolean(Context context, Boolean value) {

		String tmp = "";

		if (value) {

			tmp = "Yes";

		} else {

			tmp = "No";
		}
		return tmp;

	}
	
	
	
	public static String SubStringPrice(String text) {

	
		
		String[] separated = text.split(" ");

		String price = separated[0];
		String unt = separated[1];

		
		/*String[] priceString = price.split(".");
		String priceOnly = priceString[0];*/

		//int priceint = Integer.parseInt(price);

		
		//String number = "1000500000.574";
		double amount = Double.parseDouble(price);
		DecimalFormat formatter = new DecimalFormat("#,###.00");


		

        String full = formatter.format(amount)+" "+unt;
        return full;
        
		
	        
	        
	        
	}
	
	/*::  Distiantions       :*/
	public static List<JSONObject> ValueDistiantions(JSONArray GetHotelsAvailableDATA, double locationLatitude, double locationLongitude, String unitDistanceResult) {
		
		
		  List<JSONObject> jsonValueDistiantions = new ArrayList<JSONObject>();
		  JSONArray jsonarrayAdd= null;
		 
			 
				 jsonarrayAdd = GetHotelsAvailableDATA ;
				 
					for (int i = 0; i < jsonarrayAdd.length(); i++) {
						try {
							jsonarrayAdd.getJSONObject(i).get("latitude");
							jsonarrayAdd.getJSONObject(i).get("longitude");
							double lat = Double.parseDouble(jsonarrayAdd.getJSONObject(i).get("latitude").toString());
							double lng = Double.parseDouble(jsonarrayAdd.getJSONObject(i).get("longitude").toString());
							   double  kkm= distance(locationLatitude,locationLongitude,lat,lng,unitDistanceResult);
							   double format_number = kkm;
								int decimalPlaces = 1;
								BigDecimal bd = new BigDecimal(format_number);
								// setScale is immutable 
								bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
								format_number = bd.doubleValue(); 
							    jsonarrayAdd.getJSONObject(i).put("Distination", format_number);
							    jsonValueDistiantions.add(jsonarrayAdd.getJSONObject(i));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		 
		return jsonValueDistiantions;

	}
	public static  double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  if (unit.equals("K") ) {
		    dist = dist * 1.609344;
		  } else if (unit.equals("N")) {
		  	dist = dist * 0.8684;
		    }
		  return (dist);
		}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts decimal degrees to radians             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	public static double deg2rad(double deg) {
	  return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts radians to decimal degrees             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	public static double rad2deg(double rad) {
	  return (rad * 180 / Math.PI);
	}
	

}
