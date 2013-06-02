package kiwi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import kiwi.utils.DatabaseConnector;
import kiwi.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 * Filter implementujący wzorzec transaction-per-request. Każdy request objęty jest
 * transakcją, dostęp do obiektu sesji przez HibernateUtil.getSessionFactory().getCurrentSession().
 * @author pawel
 */
@WebFilter(filterName="transactionFilter")
public class TransactionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Session session = DatabaseConnector.getInstance().getSessionFactory().openSession();
		ThreadLocalSessionContext.bind(session);
		session.beginTransaction();
		
		chain.doFilter(request, response);
		
		ThreadLocalSessionContext.unbind(DatabaseConnector.getInstance().getSessionFactory());
		session.getTransaction().commit();
		//session.close();
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
   
	public void destroy() {	
	}

}
