package in.tum.de.aics.model;

public class Location {
	public String locationId;
	public String lng;
	public String lat;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String placeId) {
		this.locationId = placeId;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}
