package kiwi.model;

import java.sql.Time;

import javax.persistence.*;
/**
 * Lot
 * @author bartek
 */
@Entity
@Table(name = "Flight")
public class Flight {
	@Id @GeneratedValue
    @Column(name = "flightId")
    private Long flightId;
	
	@ManyToOne  
    @JoinColumn(name = "depAirport_id")
	private Airport depAirport;
     
    @ManyToOne  
    @JoinColumn(name = "arrAirport_id")
    private Airport arrAirport;
	private double staticPrice;
	private Time depTime;
	private Time arrTime;
	
	// private WeekDay
	
	@ManyToOne  
    @JoinColumn(name = "flight_id")
	private AirlineCompanyUser airlineCompany;
	
	public Flight() {}

	public Airport getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}

	public Airport getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(Airport arrAirport) {
		this.arrAirport = arrAirport;
	}

	public double getStaticPrice() {
		return staticPrice;
	}

	public void setStaticPrice(double staticPrice) {
		this.staticPrice = staticPrice;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public AirlineCompanyUser getAirlineCompany() {
		return airlineCompany;
	}

	public void setAirlineCompany(AirlineCompanyUser airlineCompany) {
		this.airlineCompany = airlineCompany;
	}
	
	
}
