package in.tum.de.aics.services.interfaces;

import java.util.List;

import in.tum.de.aics.model.Location;
import in.tum.de.aics.model.Place;

public interface IPlacesService {
	public Place findPlaceById(String placeId);

	public List<Place> findPlacesByLocation(Location location);

	public List<Place> autoComplete(String city);
	
	public List<Place> searchPlaces(String keyword, double lat, double lng, int radius);
	
	public Place findPlaceDetail(String reference);
	
	public List<Place> nearbySearch(String lng, String lat);

	public List<Place> radarSearch(String lng, String lat, String[] typeArray);
}
