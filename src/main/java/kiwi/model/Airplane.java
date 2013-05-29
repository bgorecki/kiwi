package kiwi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Airplane")
public class Airplane {
	@Id @GeneratedValue
	private long airplaneId;
	
	@ManyToOne  
    @JoinColumn(name = "carrierId")
	private Carrier carrier;
	
	@Transient
	private Places[] places;
	private Double weight;
	private Double size;
	private Double fuelConsumptionPerHour;
	private String name;
	
	public Airplane() {}

	public long getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(long airplaneId) {
		this.airplaneId = airplaneId;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
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

	public double getFuelConsumptionPerHour() {
		return fuelConsumptionPerHour;
	}

	public void setFuelConsumptionPerHour(double fuelConsumptionPerHour) {
		this.fuelConsumptionPerHour = fuelConsumptionPerHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPlace(int index, Places place) {
		places[index] = place;
	}
	
	/**
	 * Metoda sprawdzająca, czy wszystkie pola z formularza są uzupełnione
	 * @return true, jeśli wszystkie pola są poprawnie uzupełnione, false jeśli czegoś brakuje
	 */
    public boolean isFullyFilled() {
    	if(this.name==null || this.name.equals(""))
        	return false;
        if(this.weight==null || this.weight.equals(""))
            return false;
        if(this.size==null || this.size.equals(""))
            return false;
        if(this.fuelConsumptionPerHour==null || this.fuelConsumptionPerHour.equals(""))
            return false;
        return true;
    }
}
