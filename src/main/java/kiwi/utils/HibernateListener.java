package kiwi.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateListener implements ServletContextListener
{

	public void contextInitialized(ServletContextEvent event)
	{
		// Just call the static initializer of that class
		DatabaseConnector.getInstance().getSessionFactory();
		HibernateUtil.createDataForTesting();
	}

	public void contextDestroyed(ServletContextEvent event)
	{
		// Free all resources
		DatabaseConnector.getInstance().getSessionFactory().close();
	}
}
