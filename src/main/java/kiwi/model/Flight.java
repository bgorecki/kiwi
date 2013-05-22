package kiwi.model;

import java.sql.Time;
import java.util.List;


public class Flight {
	private WeekDay WEEK_DAY;
	private Airport depAirport;
	private Airport arrAirport;
	private double staticPrice;
	private Time depTime;
	private Time arrTime;
	private List<Reservation> reservations;
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

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	
}
