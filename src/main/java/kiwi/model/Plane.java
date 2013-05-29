package kiwi.model;

public class Plane {
	private AirlineCompany airlineCompany;
	private Places[] places;
	private double weight;
	private double size;
	private double fuelConsume;
	private String name;
	
	public Plane() {}
	
	public AirlineCompany getAirlineCompany() {
		return airlineCompany;
	}

	public void setAirlineCompany(AirlineCompany airlineCompany) {
		this.airlineCompany = airlineCompany;
	}

	public Places[] getPlaces() {
		return places;
	}

	public void setPlaces(Places[] places) {
		this.places = places;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getFuelConsume() {
		return fuelConsume;
	}

	public void setFuelConsume(double fuelConsume) {
		this.fuelConsume = fuelConsume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
