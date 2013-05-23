package kiwi.dao;

import java.io.Serializable;

import kiwi.utils.HibernateUtil;

import org.hibernate.Session;

/**
 * Klasa implementująca operacje CRUD dla wszystkich DAO, które z niej będą dziedziczyć.
 * @author pawel
 *
 * @param <T> Typ obieku, który obsługuje DAO
 * @param <PK> Typ klucza prywatnego.
 */
public class GenericDao <T, PK extends Serializable>{
	private Class<?> type;
	
	public GenericDao(Class<?> type) {
		this.type = type;
	}
	
	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public PK create(T object) {
		return (PK) getSession().save(object);
	}
	
	public T read(PK id) {
		return (T) getSession().get(type, id);
	}
	
	public void update(T object) {
		getSession().update(object);
	}
	
	public void delete(T object) {
		getSession().delete(object);
	}

}
