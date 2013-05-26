package kiwi.model;

import java.util.List;

import javax.persistence.*;
/**
 * Przewoźnik
 * @author bartek
 */
@Entity
@Table(name = "CARRIER")
public class Carrier {

	@Id @GeneratedValue
	@Column(name = "carrierId")
	private Long carrierId;
	
	@OneToMany(mappedBy="carrier", cascade=CascadeType.ALL)
	private List<Flight> flights;
	
	@OneToMany(mappedBy="carrier", cascade=CascadeType.ALL)
	private List<UserCarrier> userCarrier;
	
	private String name;
	private String country;
    
	@Transient
	private List<Airplane> airplanes;
    @Transient
	private List<Modifier> modificator;
	@Transient
	private List<Employee> employees;
	
	public Carrier() {}

	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
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

	public List<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(List<Airplane> airplanes) {
		this.airplanes = airplanes;
	}

	public List<Modifier> getModificator() {
		return modificator;
	}

	public void setModificator(List<Modifier> modificator) {
		this.modificator = modificator;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<UserCarrier> getUserCarrier() {
		return userCarrier;
	}

	public void setUserCarrier(List<UserCarrier> userCarrier) {
		this.userCarrier = userCarrier;
	}
}
