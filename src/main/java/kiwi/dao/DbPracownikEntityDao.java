package kiwi.dao;

import java.util.List;

import kiwi.models.DbLspEntity;
import kiwi.models.DbPracownikEntity;

public class DbPracownikEntityDao extends GenericDao<DbPracownikEntity, Integer> {
	public DbPracownikEntityDao() {
		super(DbPracownikEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DbPracownikEntity> getAll() {
		getSession().beginTransaction();
		List<DbPracownikEntity> employees = (List<DbPracownikEntity>)getSession().createQuery("from kiwi.models.DbPracownikEntity").list();
		getSession().getTransaction().commit();
		getSession().clear();
    	return employees;
	}

}
