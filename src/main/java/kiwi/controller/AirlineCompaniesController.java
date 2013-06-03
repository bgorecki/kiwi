package kiwi.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.GenericDao;
import kiwi.models.DbPrzewoznikEntity;
import kiwi.models.DbUzytkownikEntity;
import kiwi.utils.DatabaseConnector;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/airlinecompanies")
public class AirlineCompaniesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		GenericDao<DbPrzewoznikEntity, Integer> dao = new GenericDao<DbPrzewoznikEntity, Integer>(DbPrzewoznikEntity.class);
		
		if(action == null) {
			// Domyślnie wyświetlaj przewoźników
			List<DbPrzewoznikEntity> airlineCompanies = dao.getAll();
			request.setAttribute("airlineCompanies", airlineCompanies);
			request.getRequestDispatcher("showAirlineCompanies.jsp").forward(request, response);
			return;
		}
		
		if(action.equals("addCompany")) {
			request.getRequestDispatcher("addAirlineCompany.jsp").forward(request, response);
			return;
		}
		
		if(action.equals("editCompany")) {
			String id = request.getParameter("id");

			if(!checkIfIdParamIsCorrect(id)) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			DbPrzewoznikEntity przewoznik = dao.read(Integer.valueOf(id));
			request.setAttribute("airlineCompany", przewoznik);
			request.getRequestDispatcher("editAirlineCompany.jsp").forward(request, response);
			return;
		}
		
		if(action.equals("deleteCompany")) {
			String id = request.getParameter("id");
			if(!checkIfIdParamIsCorrect(id)) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			DatabaseConnector.getInstance().getSession().beginTransaction();
			DbPrzewoznikEntity przewoznik = dao.read(Integer.valueOf(id));
			dao.delete(przewoznik);
			DatabaseConnector.getInstance().getSession().getTransaction().commit();
			response.sendRedirect("airlinecompanies");
			return;
		}
		
		if(action.equals("deleteCompanyUser")) {
			String id = request.getParameter("id");
			if(!checkIfIdParamIsCorrect(id)) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			GenericDao<DbUzytkownikEntity, Integer> userDao = new GenericDao<DbUzytkownikEntity, Integer>(DbUzytkownikEntity.class);
			DatabaseConnector.getInstance().getSession().beginTransaction();
			DbUzytkownikEntity user = userDao.read(Integer.valueOf(id));
			user.getPrzewoznikByIdPrzewoznika().getUzytkowniksByIdPrzewoznika().remove(user);
			
			userDao.delete(user);
			DatabaseConnector.getInstance().getSession().getTransaction().commit();
			response.sendRedirect("airlinecompanies?action=editCompany&id="+ user.getPrzewoznikByIdPrzewoznika().getIdPrzewoznika());
			return;
		}
		
		// Nie dopasowano akcji
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	
	private boolean checkIfIdParamIsCorrect(String id) {
		if(id == null || id.equals("")) {
			return false;
		}
		
		try {
			Integer.valueOf(id);
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		GenericDao<DbPrzewoznikEntity, Integer> dao = new GenericDao<DbPrzewoznikEntity, Integer>(DbPrzewoznikEntity.class);
		
		if(action.equals("addCompany")) {
			if(!allRequiredFieldsFilledInAddForm(request)) {
				request.setAttribute("msg", "Wszystkie wymagane pola muszą być uzupełnione!");
				request.getRequestDispatcher("addAirlineCompany.jsp").forward(request, response);
				return;
			}
			
			if(!checkIfPasswordIsCorrectlyTyped(request)) {
				request.setAttribute("msg", "Hasło oraz hasło powtórzone muszą być takie same!");
				request.getRequestDispatcher("addAirlineCompany.jsp").forward(request, response);
				return;
			}
			
			DbPrzewoznikEntity przewoznik = new DbPrzewoznikEntity();
			DbUzytkownikEntity uzytkownikPrzewoznika = new DbUzytkownikEntity();
			try {
				BeanUtils.populate(przewoznik, request.getParameterMap());
				BeanUtils.populate(uzytkownikPrzewoznika, request.getParameterMap());
				uzytkownikPrzewoznika.setRola(DbUzytkownikEntity.PRZEWOZNIK_ROLE);
				uzytkownikPrzewoznika.setPrzewoznikByIdPrzewoznika(przewoznik);
				
				List<DbUzytkownikEntity> uzytkownicyPrzewoznika = new LinkedList<DbUzytkownikEntity>();
				uzytkownicyPrzewoznika.add(uzytkownikPrzewoznika);
				przewoznik.setUzytkowniksByIdPrzewoznika(uzytkownicyPrzewoznika);
				
				dao.create(przewoznik);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			} 
			
			response.sendRedirect("airlinecompanies");
			return;
		}
		
		if(action.equals("editCompany")) {
			String nazwa = request.getParameter("nazwa");
			String kraj = request.getParameter("kraj");
			if(nazwa == null || nazwa.equals("") || kraj == null || kraj.equals("")) {
				request.setAttribute("msg", "Wszystkie wymagane pola muszą być uzupełnione!");
				request.getRequestDispatcher("editAirlineCompany.jsp").forward(request, response);
				return;
			}
			
			DbPrzewoznikEntity przewoznik = new DbPrzewoznikEntity();
			try {
				przewoznik = dao.read(Integer.parseInt(request.getParameter("id")));
				BeanUtils.populate(przewoznik, request.getParameterMap());
				DatabaseConnector.getInstance().getSession().beginTransaction();
				dao.update(przewoznik);
				DatabaseConnector.getInstance().getSession().getTransaction().commit();
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			
			response.sendRedirect("airlinecompanies?action=editCompany&id=" + przewoznik.getIdPrzewoznika());
			return;
		}
		
		if(action.equals("addCompanyUser")) {
			String companyId = request.getParameter("companyId");
			DbPrzewoznikEntity przewoznik = dao.read(Integer.valueOf(companyId));
			
			DbUzytkownikEntity uzytkownikPrzewoznika = new DbUzytkownikEntity();
			try {
				BeanUtils.populate(uzytkownikPrzewoznika, request.getParameterMap());
				uzytkownikPrzewoznika.setRola(DbUzytkownikEntity.PRZEWOZNIK_ROLE);
				uzytkownikPrzewoznika.setPrzewoznikByIdPrzewoznika(przewoznik);
				
				DatabaseConnector.getInstance().getSession().beginTransaction();
				GenericDao<DbUzytkownikEntity, Integer> userDao = new GenericDao<DbUzytkownikEntity, Integer>(DbUzytkownikEntity.class);
				userDao.create(uzytkownikPrzewoznika);
				DatabaseConnector.getInstance().getSession().getTransaction().commit();
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			
			response.sendRedirect("airlinecompanies?action=editCompany&id=" + przewoznik.getIdPrzewoznika());
			return;

		}
	}
	
	private boolean allRequiredFieldsFilledInAddForm(HttpServletRequest request) {
		String nazwa = request.getParameter("nazwa");
		String kraj = request.getParameter("kraj");
		String login = request.getParameter("login");
		String haslo = request.getParameter("haslo");
		String hasloPowtorzone = request.getParameter("hasloPowtorzone");
		
		if(nazwa == null || nazwa.equals("") ||
		   kraj == null || kraj.equals("") ||
		   login == null || kraj.equals("") || 
		   haslo == null || haslo.equals("") || 
		   hasloPowtorzone == null || hasloPowtorzone.equals("")) { 
			return false;
		}
		
		return true;
	}
	
	private boolean checkIfPasswordIsCorrectlyTyped(HttpServletRequest request) {
		String password = request.getParameter("haslo");
		String passwordRepeated = request.getParameter("hasloPowtorzone");
		
		return password.equals(passwordRepeated);
	}

}
