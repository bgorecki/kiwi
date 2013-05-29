package kiwi.dao;

import java.util.List;
import kiwi.model.Airport;

/**
 *
 * @author bartek
 */
public class AirportDao extends GenericDao<Airport, Long> {
    
    public AirportDao() {
        super(Airport.class);
    }
    
    /**
     * Pobieranie wszystkich lotnisk z bazy
     * @return Lista wszystkich lotnisk
     */
    @SuppressWarnings("unchecked")
	public List<Airport> getAll() {
    	List<Airport> airports = (List<Airport>)getSession().createQuery("from kiwi.model.Airport").list();
    	return airports;
    }
}
