package kiwi.dao;

import java.util.List;

import kiwi.model.Airplane;
import kiwi.model.Airport;

public class AirplaneDao extends GenericDao<Airplane, Long>{

	public AirplaneDao() {
		super(Airplane.class);
	}
	
	public List<Airplane> getAll() {
		List<Airplane> airplanes = (List<Airplane>)getSession().createQuery("from kiwi.model.Airplane").list();
    	return airplanes;
	}

}
