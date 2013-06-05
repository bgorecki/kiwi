package kiwi.dao;

import java.util.List;

import kiwi.models.DbLotniskoEntity;
import kiwi.models.DbSamolotEntity;

public class DbSamolotEntityDao extends GenericDao<DbSamolotEntity, Integer>{

	public DbSamolotEntityDao() {
		super(DbSamolotEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DbSamolotEntity> getAll() {
		List<DbSamolotEntity> airplanes = (List<DbSamolotEntity>)getSession().createQuery("from kiwi.models.DbSamolotEntity").list();
    	return airplanes;
	}
	
	public void update(DbSamolotEntity airplane) {
		getSession().beginTransaction();
		getSession().update(airplane);
		getSession().getTransaction().commit();
	}
	
	public void delete(Integer id) {
		getSession().beginTransaction();
		DbSamolotEntity airplane = new DbSamolotEntityDao().read(id);
    	new DbSamolotEntityDao().delete(airplane);
    	getSession().getTransaction().commit();
	}
	
	/**
	 * Metoda sprawdzająca, czy wszystkie pola z formularza są uzupełnione
	 * @return true, jeśli wszystkie pola są poprawnie uzupełnione, false jeśli czegoś brakuje
	 */
    public boolean isFullyFilled(DbSamolotEntity airplane) {
    	if(airplane.getNazwa()==null || airplane.getNazwa().equals(""))
        	return false;
    	if(airplane.getWaga()==null || airplane.getWaga().equals(""))
        	return false;
    	if(airplane.getWielkosc()==null || airplane.getWielkosc().equals(""))
        	return false;
    	if(airplane.getZuzyciePaliwa()==null || airplane.getZuzyciePaliwa().equals(""))
        	return false;
        return true;
    }

}
