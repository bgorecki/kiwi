package kiwi.model;

import java.util.List;


public class Carrier extends User{
/*
 * dodać users - UserPrzewoznik[]
 */
	private List<Flight> flights;
	private String name;
	private String country;
        private List<Airplane> airplanes;
        private List<Modifier> modificator;
	private List<Employee> employees;
	
	public Carrier() {}
}
