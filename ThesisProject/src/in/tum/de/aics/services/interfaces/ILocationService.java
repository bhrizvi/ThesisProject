package in.tum.de.aics.services.interfaces;

import in.tum.de.aics.model.Location;

public interface ILocationService {
	public Location findLocationByCity(String City);
	public Location findLocationByLocationId(String locationId);
}
