package kiwi.dao;

import java.util.List;

import com.google.common.collect.Iterables;
import kiwi.models.DbLotniskoEntity;

/**
 *
 * @author bartek
 */
public class DbLotniskoEntityDao extends GenericDao<DbLotniskoEntity, Integer> {
    
    public DbLotniskoEntityDao() {
        super(DbLotniskoEntity.class);
    }
    
    /**
     * Pobieranie wszystkich lotnisk z bazy
     * @return Lista wszystkich lotnisk
     */
    @SuppressWarnings("unchecked")
	public List<DbLotniskoEntity> getAll() {
    	List<DbLotniskoEntity> airports = (List<DbLotniskoEntity>)getSession().createQuery("from kiwi.models.DbLotniskoEntity").list();

    	return airports;
    }
    
    public void delete(Integer id) {
    	getSession().beginTransaction();
    	DbLotniskoEntity airport = new DbLotniskoEntityDao().read(id);
    	new DbLotniskoEntityDao().delete(airport);
    	getSession().getTransaction().commit();
	}
    
	/**
	 * Metoda sprawdzająca, czy wszystkie pola z formularza są uzupełnione
	 * @return true, jeśli wszystkie pola są poprawnie uzupełnione, false jeśli czegoś brakuje
	 */
    public boolean isFullyFilled(DbLotniskoEntity airport) {
    	if(airport.getMiasto()==null || airport.getMiasto().equals(""))
        	return false;
        if(airport.getPanstwo()==null || airport.getPanstwo().equals(""))
            return false;
        if(airport.getNazwa()==null || airport.getNazwa().equals(""))
            return false;
        return true;
    }

	@SuppressWarnings("unchecked")
	public DbLotniskoEntity getByName(String s)
	{
		return Iterables.<DbLotniskoEntity>getFirst(getSession().createQuery("from DbLotniskoEntity where nazwa = :name").setParameter("name", s).list(),null);
	}
}
