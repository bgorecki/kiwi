package kiwi.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import kiwi.models.DbRezerwacjaEntity;
import kiwi.models.DbUzytkownikEntity;

public class RezerwacjaDao extends GenericDao<DbRezerwacjaEntity, Integer>{

	public RezerwacjaDao() {
		super(DbRezerwacjaEntity.class);
	}
	
	public DbRezerwacjaEntity getByIdAndKod(Integer id, String kod) {
		DbRezerwacjaEntity re = null;
		
		Criteria criteria = getSession().createCriteria(DbRezerwacjaEntity.class);
		criteria.add(Restrictions.eq("idRezerwacji", id));
		criteria.add(Restrictions.eq("kodAutoryzacyjny", kod));
		
		if(!criteria.list().isEmpty()) {
			re = (DbRezerwacjaEntity) criteria.list().get(0);
		}
		
		return re;
	}

}
