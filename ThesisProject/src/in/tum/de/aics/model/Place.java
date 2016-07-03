package in.tum.de.aics.model;

public class Place {
	public String placeId;
	public String fourSquareName;
	public String googlePlaceName;
	public String lng;
	public String lat;
	public String[] fourSquareCategory;
	public String rating;
	public String icon;
	public String reference;
	public String formattedAddress;

	public String getFourSquareName() {
		return fourSquareName;
	}

	public void setFourSquareName(String fourSquareName) {
		this.fourSquareName = fourSquareName;
	}

	public String getGooglePlaceName() {
		return googlePlaceName;
	}

	public void setGooglePlaceName(String googlePlaceName) {
		this.googlePlaceName = googlePlaceName;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public String formattedPhoneNumber;
	public Location location;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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

	public String[] getFourSquareCategory() {
		return fourSquareCategory;
	}

	public void setFourSquareCategory(String[] fourSquareCategory) {
		this.fourSquareCategory = fourSquareCategory;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
