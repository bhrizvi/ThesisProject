package in.tum.de.aics.services.interfaces;

import java.util.List;
import java.util.Map;

import in.tum.de.aics.model.Place;

public interface IScoringService {
	public Map<Place, Float> scorePlaces(List<Place> sortedPlaces);
	public double calculateBayesianEstimates(List<Place> places);
	public double calculateFinalScore();
}
