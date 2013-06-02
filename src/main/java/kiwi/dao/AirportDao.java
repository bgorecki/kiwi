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
    	return (List<Airport>)getSession().createQuery("from DbLotniskoEntity").list();
    }
}
