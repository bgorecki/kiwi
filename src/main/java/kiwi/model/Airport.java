package kiwi.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Lotnisko
 * @author bartek
 * TO DO : implementacja metod getDepartureFlightByDate(), getArrivalFlightsByDate()
 */
@Entity
@Table(name = "Airport")
public class Airport implements Serializable {
    @Id @GeneratedValue
    @Column(name = "airportId")
    private Long airportId;

    private String code; // 3-literowy kod lotniska
	private String country;
	private String city;
        
    @OneToMany(mappedBy="depAirport", cascade=CascadeType.ALL)
	private List<Flight> departures = new ArrayList<Flight>();
        
    @OneToMany(mappedBy="arrAirport", cascade=CascadeType.ALL)
	private List<Flight> arrivals = new ArrayList<Flight>();
    
	public Airport() {}
	
	public Airport(String code, String country, String city) {
		this.code = code;
		this.country = country;
		this.city = city;
	}
        
        public Long getAirportId() {
            return airportId;
        }

        public void setAirportId(Long airportId) {
            this.airportId = airportId;
        }
	
	public List<Flight> getDepartureFlightByDate() {
		return null;
	}
	
	public List<Flight> getArrivalFlightsByDate() {
		return null;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public boolean addDeparture(Flight departure) {
		getDepartures().add(departure);
		return true;
	}
	
	public boolean deleteDeparture(Flight departure) {
		return true;
	}
	
	public boolean addArrival(Flight arrival) {
		getArrivals().add(arrival);
		return true;
	}

	public boolean deleteArrival(Flight arrival) {
		
		return true;
	}

	public List<Flight> getDepartures() {
		return departures;
	}

	public void setDepartures(List<Flight> departures) {
		this.departures = departures;
	}

	public List<Flight> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Flight> arrivals) {
		this.arrivals = arrivals;
	}
        
	/**
	 * Metoda sprawdzająca, czy wszystkie pola z formularza są uzupełnione
	 * @return true, jeśli wszystkie pola są poprawnie uzupełnione, false jeśli czegoś brakuje
	 */
    public boolean isFullyFilled() {
    	if(this.city==null || this.city.equals(""))
        	return false;
        if(this.country==null || this.country.equals(""))
            return false;
        if(this.code==null || this.code.equals(""))
            return false;
        return true;
    }
}
