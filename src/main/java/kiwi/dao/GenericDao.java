package kiwi.dao;

import java.io.Serializable;

import kiwi.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Klasa implementująca operacje CRUD dla wszystkich DAO, które z niej będą dziedziczyć.
 * @author pawel
 *
 * @param <T> Typ obieku, który obsługuje DAO
 * @param <PK> Typ klucza prywatnego.
 */
public class GenericDao <T, PK extends Serializable>{
	private SessionFactory sessionFactory;
	private Class<?> type;
	
	public GenericDao(Class<?> type) {
		this.type = type;
		
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	public PK create(T object) {
		PK id;
		
		Session session = getSession();
		session.beginTransaction();
		id = (PK) session.save(object);
		session.getTransaction().commit();
		session.close();
		
		return id;
	}
	
	public T read(PK id) {
		T object;
		
		Session session = getSession();
		session.beginTransaction();
		object = (T) session.get(type, id);
		session.getTransaction().commit();
		session.close();
		
		return object;
	}
	
	public void update(T object) {
		Session session = getSession();
		session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(T object) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
		session.close();
	}

}
