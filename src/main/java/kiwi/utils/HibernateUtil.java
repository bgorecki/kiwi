package kiwi.utils;

import kiwi.dao.UserDao;
import kiwi.model.User;
import kiwi.model.AdminUser;
import kiwi.model.AirlineCompanyUser;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class HibernateUtil {
	 
    private static final SessionFactory sessionFactory;
 
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            createDataForTesting();   		
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
 // TODO Usunąć w późniejszej fazie
    static private void createDataForTesting() {
    	Session s = sessionFactory.openSession();
    	ThreadLocalSessionContext.bind(s);
        s.beginTransaction();
  
        
		User user = new AirlineCompanyUser();
		user.setPassword("123");
		user.setUsername("jan kowalski");
		new UserDao().create(user);
		
		user = new AdminUser();
		user.setPassword("admin1");
		user.setUsername("admin1");
		new UserDao().create(user);
		
		
		ThreadLocalSessionContext.unbind(sessionFactory);
		s.getTransaction().commit();
		s.close();
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
}
