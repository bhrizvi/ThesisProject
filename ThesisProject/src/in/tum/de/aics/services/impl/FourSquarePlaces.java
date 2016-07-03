package in.tum.de.aics.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.tum.de.aics.model.Location;
import in.tum.de.aics.model.Place;

public class FourSquarePlaces {

	private static final String PLACES_API_BASE = "https://api.foursquare.com/v2/venues";
	private static final String TYPE_SEARCH = "/search";
	private static final String TYPE_EXPLORE = "/explore";
	private static final String CLIENT_ID = "QKTLOP2J3552ZVV1QTAX0H1W3BI0CVTGTSQ2ZBYGMJB2LZLE";
	private static final String CLIENT_SECRET = "3YNBS3VGETCNGYYVGOE4HH1SZTVO010FASBERLH21NGUQYF2";
	private static final float NO_RATING = 0F;
	private static final Integer ZERO_LIKES = 0;

	public FourSquarePlaces() {
	}

	public ArrayList<Place> search(double lat, double lng, int radius, String categoriesID, int limit) {

		ArrayList<Place> resultList = null;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_SEARCH);
			sb.append("?client_id=" + CLIENT_ID);
			sb.append("&client_secret=" + CLIENT_SECRET);
			sb.append("&radius=" + String.valueOf(radius));
			sb.append("&ll=" + String.valueOf(lat) + "," + String.valueOf(lng));
			sb.append("&global=true");
			sb.append("&categoryId=" + categoriesID);
			sb.append("&limit=" + limit);

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			return resultList;
		} catch (IOException e) {
			return resultList;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());

			JSONObject ResponseJsonObj = new JSONObject(jsonObj.get("response").toString());
			JSONArray predsJsonArray = ResponseJsonObj.getJSONArray("venues");

			// System.out.print(predsJsonArray);
			// Extract the Place descriptions from the results
			resultList = new ArrayList<Place>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {

				Place place = new Place();

				place.setFourSquareName(predsJsonArray.getJSONObject(i).getString("name"));

				JSONArray catJsonArray = predsJsonArray.getJSONObject(i).getJSONArray("categories");
				StringBuilder categories = new StringBuilder();
				for (int j = 0; j < catJsonArray.length(); j++) {
					categories.append(catJsonArray.getJSONObject(j).getString("name") + ",");
				}

				JSONObject stats = predsJsonArray.getJSONObject(i).getJSONObject("stats");
				resultList.add(place);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public ArrayList<Place> explore(double lat, double lng, int radius, int limit, String city) {

		ArrayList<Place> resultList = null;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_EXPLORE);
			sb.append("?client_id=" + CLIENT_ID);
			sb.append("&client_secret=" + CLIENT_SECRET);
			sb.append("&radius=" + String.valueOf(radius));
			sb.append("&near=" + city);
			// sb.append("&ll=" + String.valueOf(lat) + "," +
			// String.valueOf(lng));
			sb.append("&global=true");
			sb.append("&limit=" + "5");
			sb.append("&v=20160815");
			sb.append("&query=museum");

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONObject responseJsonObj = new JSONObject(jsonObj.get("response").toString());
			JSONArray groupJsonArray = responseJsonObj.getJSONArray("groups");
			JSONArray itemJsonArray = groupJsonArray.getJSONObject(0).getJSONArray("items");

			resultList = new ArrayList<Place>(itemJsonArray.length());

			for (int i = 0; i < itemJsonArray.length(); i++) {

				Place place = new Place();
				JSONObject venueJsonObj = itemJsonArray.getJSONObject(i).getJSONObject("venue");

				String id = venueJsonObj.get("id").toString();

				/*place.setFourSquareName(venueJsonObj.getString("name"));

				String geometry = venueJsonObj.getJSONObject("location").toString();

				JSONObject geometryJsonObj;
				geometryJsonObj = new JSONObject(geometry);
				place.setLng(geometryJsonObj.getString("lng"));
				place.setLat(geometryJsonObj.getString("lat"));*/

				venueDetails(id);
				// place.setLat(Double.parseDouble(geometryJsonObj.getString("lat")));

				/*
				 * JSONArray catgoryJsonArray =
				 * venueJsonObj.getJSONArray("categories"); StringBuilder
				 * categories = new StringBuilder(); for (int j = 0; j <
				 * catgoryJsonArray.length(); j++) {
				 * categories.append(catgoryJsonArray.getJSONObject(j).getString
				 * ("name") + ","); }
				 */

				/*
				 * place.setTypes(categories.substring(0, categories.length() -
				 * 1));
				 * 
				 * place.setStats(venueJsonObj.getJSONObject("stats").getInt(
				 * "checkinsCount"));
				 * 
				 * if(venueJsonObj.has("likes"))
				 * place.setLikes(venueJsonObj.getJSONObject("likes").getInt(
				 * "count")); else place.setLikes(ZERO_LIKES);
				 * 
				 * if(venueJsonObj.has("hours"))
				 * place.setOpenNow(venueJsonObj.getJSONObject("hours").
				 * getBoolean("isOpen"));
				 * 
				 * if(venueJsonObj.has("rating"))
				 * place.setRating(Float.parseFloat(venueJsonObj.getString(
				 * "rating"))); else place.setRating(NO_RATING);
				 * 
				 * if(venueJsonObj.has("ratingSignals"))
				 * place.setRatingVotes(venueJsonObj.getInt("ratingSignals"));
				 * else place.setRatingVotes(0);
				 */
				resultList.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public Place venueDetails(String placeId) {

		Place place = new Place();
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		Location location = new Location();
		String[] categoryArray = null;

		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append("/");
			sb.append(placeId);
			sb.append("?client_id=" + CLIENT_ID);
			sb.append("&client_secret=" + CLIENT_SECRET);
			sb.append("&v=20160815");

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[1024];

			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject((new JSONObject(jsonResults.toString()).get("response")).toString());

			JSONObject venueObject = new JSONObject(jsonObj.get("venue").toString());
			// JSONArray predsJsonArray =
			// ResponseJsonObj.getJSONArray("venues");

			if (venueObject.has("icon")) {
				place.setIcon(venueObject.getString("icon"));
			}

			if (venueObject.has("name")) {
				String name = new String(venueObject.getString("name").getBytes(Charset.forName("ISO-8859-1")),
						Charset.forName("UTF-8"));
				place.setFourSquareName(name);
			}

			if (venueObject.has("place_id")) {
				place.setPlaceId(venueObject.getString("place_id"));
			}

			if (venueObject.has("rating")) {
				place.setRating(venueObject.getString("rating"));
			}

			if (venueObject.has("ratingSignals")) {
				place.setRating(venueObject.getString("ratingSignals"));
			}

			if (venueObject.has("categories")) {
				JSONArray js = new JSONArray(venueObject.getString("categories"));
				categoryArray = new String[js.length()];

				for (int j = 0; j < js.length(); j++) {
					String input = js.getJSONObject(j).getString("name");
					categoryArray[j] = new String(input.getBytes(Charset.forName("ISO-8859-1")),
							Charset.forName("UTF-8"));

				}

				place.setFourSquareCategory(categoryArray);
			}

			location.setLng(venueObject.getJSONObject("location").getString("lng"));
			location.setLat(venueObject.getJSONObject("location").getString("lat"));
			place.setLocation(location);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return place;
	}
}