package kiwi.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import kiwi.model.User;

/**
 * Filtr sprawdzający czy osoba ma prawo dostępu do danego Controllera/strony JSP/zasobu.
 * Reguły definiuje się w pliku WEB-INF/accessRulesForUsers.xml.
 * @author pawel
 */
@WebFilter(filterName="authenticationFilter")
public class AuthenticationFilter implements Filter {
	private Map<String, List<Class<? extends User>>> usersWhoHasAccessToPaths = new HashMap<String, List<Class<? extends User>>>();
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestedPath = httpRequest.getServletPath();
		
		if(pathHasRestrictedAccessPrivileges(requestedPath)) {
			User user = getUserFromHttpSession(httpRequest);
			if(user == null || !userCanAccessPath(user, requestedPath)) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			} else System.out.println("User == null!");
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			String accessRulesFileRealPath = fConfig.getServletContext().getRealPath("/WEB-INF/accessRulesForUsers.xml");
			usersWhoHasAccessToPaths = new AccessRulesParser(accessRulesFileRealPath).readRulesMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}
	
	private boolean pathHasRestrictedAccessPrivileges(String path) {
		if(usersWhoHasAccessToPaths.containsKey(path)) 
			return true;
		else
			return false;
	}
	
	private User getUserFromHttpSession(HttpServletRequest request) {
		if(request.getSession(false) == null || 
		   request.getSession().getAttribute("user") == null) {
			return null;
		} else {
			return (User) request.getSession().getAttribute("user");
		}
	}
	
	private boolean userCanAccessPath(User user, String path) {
		return usersWhoHasAccessToPaths.get(path).contains(user.getClass());
	}

}