package kiwi.utils;

/**
 * Created with IntelliJ IDEA.
 * User: scroot
 * Date: 02.06.13
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: SG0205318
 * Date: 3/25/13/4:05 PM
 */
public class DatabaseConnector {
	static {
		URL resource = DatabaseConnector.class.getResource("/spring-config.xml");
		System.out.println("resource = " + resource);
	}

	private static final ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config.xml");

	private static DatabaseConnector instance;

	private final Supplier<SessionFactory> sessionFactory = Suppliers.memoize(new Supplier<SessionFactory>() {
		@Override
		public SessionFactory get() {
			return context.getBean("sessionFactory", SessionFactory.class);
		}
	});
	private Session currentSession;

	public synchronized static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory.get();
	}

	public Session getSession() {
		if (currentSession != null && currentSession.isOpen()) {
			return currentSession;
		}
		currentSession = getSessionFactory().openSession();
		return currentSession;

	}

}
