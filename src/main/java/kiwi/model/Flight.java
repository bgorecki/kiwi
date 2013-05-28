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
	
	private WeekDay WEEK_DAY;
	
	@ManyToOne  
    @JoinColumn(name = "depAirport_id")
	private Airport depAirport;
     
    @ManyToOne  
    @JoinColumn(name = "arrAirport_id")
    private Airport arrAirport;
	private double staticPrice;
	private Time depTime;
	private Time arrTime;
	
	@ManyToOne  
    @JoinColumn(name = "flight_id")
	private Carrier carrier;
	
	public Flight() {}

	public WeekDay getWEEK_DAY() {
		return WEEK_DAY;
	}

	public void setWEEK_DAY(WeekDay wEEK_DAY) {
		WEEK_DAY = wEEK_DAY;
	}

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

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	
}
