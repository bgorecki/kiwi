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

import kiwi.models.DbUzytkownikEntity;

/**
 * Filtr sprawdzający czy osoba ma prawo dostępu do danego Controllera/strony JSP/zasobu.
 * Reguły definiuje się w pliku WEB-INF/accessRulesForUsers.xml.
 * @author pawel
 */
@WebFilter(filterName="authenticationFilter")
public class AuthenticationFilter implements Filter {
	/**
	 * Mapa przechowująca ścieżki do zasobów i role użytkowników którzy mają do nich dostęp
	 * w formie <ścieżka, List<rola> >.
	 */
	private Map<String, List<String>> usersWhoHasAccessToPaths = new HashMap<String, List<String>>();
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestedPath = httpRequest.getServletPath();
		
		if(pathHasRestrictedAccessPrivileges(requestedPath)) {
			DbUzytkownikEntity user = getUserFromHttpSession(httpRequest);
			if(user == null || !userCanAccessPath(user, requestedPath)) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
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
	
	private DbUzytkownikEntity getUserFromHttpSession(HttpServletRequest request) {
		if(request.getSession(false) == null || 
		   request.getSession().getAttribute("user") == null) {
			return null;
		} else {
			return (DbUzytkownikEntity) request.getSession().getAttribute("user");
		}
	}
	
	private boolean userCanAccessPath(DbUzytkownikEntity user, String path) {
		return usersWhoHasAccessToPaths.get(path).contains(user.getRola());
	}

}
