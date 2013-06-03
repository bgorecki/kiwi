package kiwi.dao;

import kiwi.models.DbUzytkownikEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class DbUzytkownikEntityDao extends GenericDao<DbUzytkownikEntity, Integer> {

	public DbUzytkownikEntityDao() {
		super(DbUzytkownikEntity.class);
	}
	
	public DbUzytkownikEntity findByUsernameAndPassword(String username, String password) {
		DbUzytkownikEntity uzytkownik = null;
		
		Criteria criteria = getSession().createCriteria(DbUzytkownikEntity.class);
		criteria.add(Restrictions.eq("login", username));
		criteria.add(Restrictions.eq("haslo", password));
		
		if(!criteria.list().isEmpty()) {
			uzytkownik = (DbUzytkownikEntity) criteria.list().get(0);
		}
		
		return uzytkownik;
	}

}
