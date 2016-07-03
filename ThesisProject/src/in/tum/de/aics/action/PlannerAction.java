package in.tum.de.aics.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import in.tum.de.aics.model.Place;
import in.tum.de.aics.model.Planner;
import in.tum.de.aics.services.impl.FourSquarePlaces;
import in.tum.de.aics.services.impl.PlacesService;
import in.tum.de.aics.services.interfaces.IPlacesService;

public class PlannerAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Planner planner;
	private List<Place> placeList;

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}

	public String execute() {

		System.out.println(planner.getCity());
		System.out.println(planner.getDays());
		System.out.println(planner.getCategory1Score());
		System.out.println(planner.getCategory2Score());
		System.out.println(planner.getCategory3Score());
		System.out.println(planner.getCategory4Score());
		System.out.println(planner.getCategory5Score());
		System.out.println(planner.getCategory6Score());

		IPlacesService placesService = new PlacesService();
		FourSquarePlaces fs = new FourSquarePlaces();
		
		if (planner.getCity() != null) {
			placeList = fs.explore(0, 0, 5000, 5, planner.getCity());
		}

		return "success";
	}

}
