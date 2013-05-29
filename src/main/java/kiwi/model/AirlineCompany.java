package kiwi.model;

import java.util.List;

import javax.persistence.*;
/**
 * Przewoźnik
 * @author bartek
 */
@Entity
@Table(name = "AirlineCompany")
public class AirlineCompany {

	@Id @GeneratedValue
	@Column(name = "airlineCompanyId")
	private Long airlineCompanyId;
	
	@OneToMany(mappedBy="airlineCompany", cascade=CascadeType.ALL)
	private List<Flight> flights;
	
	@OneToMany(mappedBy="airlineCompany", cascade=CascadeType.ALL)
	private List<AirlineCompanyUser> airlineCompanyUsers;
	
	private String name;
	private String country;
    
	@OneToMany(mappedBy="airlineCompany", cascade=CascadeType.ALL)
	private List<Airplane> airplanes;
    @Transient
	private List<Modifier> modificator;
	@Transient
	private List<Employee> employees;
	
	public AirlineCompany() {}

	public Long getAirlineCompany() {
		return airlineCompanyId;
	}

	public void setAirlineCompany(Long airlineCompanyId) {
		this.airlineCompanyId = airlineCompanyId;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<AirlineCompanyUser> getAirlineCompanyUsers() {
		return airlineCompanyUsers;
	}

	public void setAirlineCompanyUsers(List<AirlineCompanyUser> airlineCompanyUsers) {
		this.airlineCompanyUsers = airlineCompanyUsers;
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
}
