package in.tum.de.aics.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.tum.de.aics.model.Location;
import in.tum.de.aics.model.Place;
import in.tum.de.aics.services.interfaces.IPlacesService;

public class PlacesService implements IPlacesService {

	// private static final String LOG_TAG = "ExampleApp";

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	// private static final String RADAR_SEARCH_API=
	// "https://maps.googleapis.com/maps/api/place/nearbysearch";

	private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
	private static final String TYPE_DETAILS = "/details";
	private static final String TYPE_SEARCH = "/search";
	private static final String NEARBY_SEARCH = "/nearbysearch";
	private static final String RADAR_SEARCH = "/radarsearch";

	private static final String OUT_JSON = "/json";
	private static final String OUT_XML = "/xml";

	// KEY!
	private static final String API_KEY = "AIzaSyBlFi5Xr23zJ4tPYsD-HXFl7qT3vjJmw4s"; // generated
																						// by
																						// GooglePlaces
																						// API

	URL url = null;

	@Override
	public Place findPlaceById(String placeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> findPlacesByLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> autoComplete(String city) {
		List<Place> resultList = null;

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_AUTOCOMPLETE);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&types=geocode");
			sb.append("&key=" + API_KEY);
			sb.append("&input=" + URLEncoder.encode(city, "utf8"));

			// https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Paris&types=geocode&key=YOUR_API_KEY

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<Place>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				Place place = new Place();
				place.reference = predsJsonArray.getJSONObject(i).getString("reference");
				place.googlePlaceName = predsJsonArray.getJSONObject(i).getString("description");
				resultList.add(place);
			}
		} catch (Exception e) {
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return resultList;
	}

	public List<Place> searchPlaces(String keyword, double lat, double lng, int radius) {
		List<Place> resultList = new ArrayList<Place>();

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_SEARCH);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&key=" + API_KEY);
			sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
			sb.append("&location=" + String.valueOf(lat) + "," + String.valueOf(lng));
			sb.append("&radius=" + String.valueOf(radius));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			// Log.e(LOG_TAG, "Error processing Places API URL", e);
			return resultList;
		} catch (IOException e) {
			// Log.e(LOG_TAG, "Error connecting to Places API", e);
			return resultList;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("results");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<Place>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				Place place = new Place();
				place.reference = predsJsonArray.getJSONObject(i).getString("reference");
				place.googlePlaceName = predsJsonArray.getJSONObject(i).getString("name");
				resultList.add(place);
			}
		} catch (JSONException e) {
			// Log.e(LOG_TAG, "Error processing JSON results", e);
		}

		return resultList;
	}

	public List<Place> nearbySearch(String lng, String lat) {
		List<Place> placeList = new ArrayList<Place>();

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(NEARBY_SEARCH);
			sb.append(OUT_JSON);
			sb.append("?location=" + lng + "," + lat);
			sb.append("&key=" + API_KEY);

			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("results");

			// Extract the Place descriptions from the results
			placeList = new ArrayList<Place>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				Place place = new Place();
				place.setReference(predsJsonArray.getJSONObject(i).getString("reference"));
				place.setGooglePlaceName(predsJsonArray.getJSONObject(i).getString("name"));
			//	place.setCategory(predsJsonArray.getJSONObject(i).getString("type"));
				placeList.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return placeList;
	}

	public Place findPlaceDetail(String reference) {
		Place place = new Place();
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		Location location = new Location();

		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_DETAILS);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&key=" + API_KEY);
			sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

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
			JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");
			
			place.setReference(reference);

			if (jsonObj.has("icon")) {
				place.setIcon(jsonObj.getString("icon"));
			}

			if (jsonObj.has("name")) {
				place.setGooglePlaceName(jsonObj.getString("name"));
			}

			if (jsonObj.has("place_id")) {
				place.setPlaceId(jsonObj.getString("place_id"));
			}

			if (jsonObj.has("rating")) {
				place.setRating(jsonObj.getString("rating"));
			}

			if (jsonObj.has("types")) {
			//	place.setCategory(jsonObj.getString("types"));
			}

			if (jsonObj.has("formatted_address")) {
				place.formattedAddress = jsonObj.getString("formatted_address");
			}

			if (jsonObj.has("formatted_phone_number")) {
				place.formattedPhoneNumber = jsonObj.getString("formatted_phone_number");
			}

			location.setLng(jsonObj.getJSONObject("geometry").getJSONObject("location").getString("lng"));
			location.setLat(jsonObj.getJSONObject("geometry").getJSONObject("location").getString("lat"));
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

	@Override
	public List<Place> radarSearch(String lng, String lat, String[] typeArray) {
		List<Place> placeList = new ArrayList<Place>();
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(RADAR_SEARCH);
			sb.append(OUT_JSON);
			sb.append("?location=" + lng + "," + lat);
			sb.append("&radius=5000");
			sb.append("&type=museum");
			sb.append("&key=" + API_KEY);

			// https://maps.googleapis.com/maps/api/place/radarsearch/json?location=48.1351,11.5820&radius=5000&type=museum&key=AIzaSyBlFi5Xr23zJ4tPYsD-HXFl7qT3vjJmw4s

			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("results");

			// Extract the Place descriptions from the results
			placeList = new ArrayList<Place>(predsJsonArray.length());
			String placeRef = null;
			
			for (int i = 0; i < predsJsonArray.length(); i++) {
				placeRef = predsJsonArray.getJSONObject(i).getString("reference");
				Place place = findPlaceDetail(placeRef);
				placeList.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return placeList;
	}

}
