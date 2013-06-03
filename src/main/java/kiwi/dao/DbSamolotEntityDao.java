package kiwi.dao;

import java.util.List;

import kiwi.models.DbSamolotEntity;

public class DbSamolotEntityDao extends GenericDao<DbSamolotEntity, Integer>{

	public DbSamolotEntityDao() {
		super(DbSamolotEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DbSamolotEntity> getAll() {
		getSession().beginTransaction();
		List<DbSamolotEntity> airplanes = (List<DbSamolotEntity>)getSession().createQuery("from kiwi.model.DbSamolotEntity").list();
		getSession().getTransaction().commit();
		getSession().clear();
    	return airplanes;
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
