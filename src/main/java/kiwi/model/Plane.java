package kiwi.model;

public class Plane {
	private Carrier carrier;
	private Object places;
	private double weight;
	private double size;
	private double fuelConsume;
	private String name;
	
	public Plane() {}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
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
