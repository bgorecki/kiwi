package kiwi.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "AIRPORT")
public class Airport implements java.io.Serializable {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private Long airportId;
        @Column(name = "name")
        private String name;
        @Column(name = "country")
	private String country;
        @Column(name = "city")
	private String city;
        
	private List<Flight> departures = new ArrayList<Flight>();
	private List<Flight> arrivals = new ArrayList<Flight>();
    
	
	public Airport() {}
	
	public Airport(String name, String country, String city) {
		this.name = name;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
