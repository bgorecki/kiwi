package kiwi.controller;

import kiwi.dao.DbLotniskoEntityDao;
import kiwi.dao.FlightsDao;
import kiwi.dao.GenericDao;
import kiwi.models.DbLotEntity;
import kiwi.models.DbUzytkownikEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: memorial
 * Date: 03.06.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "FlightController101", urlPatterns = {"/FlightController"})
public class FlightController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		DbLotEntity flight = new DbLotEntity();
		GenericDao<DbLotEntity, Integer> flightDao = new GenericDao<DbLotEntity, Integer>(DbLotEntity.class);

		if (action.equals("editorsave")) {
			if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
				flight = new FlightsDao().getById(Integer.parseInt(request.getParameter("id")));
			}

			Map<String, String> errors = new HashMap<>();

			try {
				HttpSession session = request.getSession();
				if(!request.getParameter("godzinaPrzylotu").matches("\\d{2}:\\d{2}:\\d{2}")) {
					errors.put("godzinaPrzylotu", "Zły format godziny przylotu");
				}
				if(!request.getParameter("godzinaWylotu").matches("\\d{2}:\\d{2}:\\d{2}")) {
					errors.put("godzinaWylotu", "Zły format godziny wylotu");
				}

				if(errors.isEmpty()) {
					BeanUtils.populate(flight, request.getParameterMap());

					flight.setLotniskoByPrzylot(new DbLotniskoEntityDao().getById(Integer.parseInt(request.getParameter("przylot"))));
					flight.setLotniskoByWylot(new DbLotniskoEntityDao().getById(Integer.parseInt(request.getParameter("wylot"))));
					flight.setPrzewoznikByIdPrzew(((DbUzytkownikEntity)session.getAttribute("user")).getPrzewoznikByIdPrzewoznika());

					request.setAttribute("lot", flight);
					if(!new FlightsDao().isFullyFilled(flight)){
						errors.put("all", "Wypełnij wszystkie pola!");
						request.setAttribute("errors",errors);
						doGet(request, response);
						//request.getRequestDispatcher("addFlights.jsp").forward(request,response);
					} else {
						if(request.getParameter("id") == null || request.getParameter("id").isEmpty()) flightDao.create(flight);
						else flightDao.update(flight);
						response.sendRedirect(request.getContextPath() + "/FlightController");
					}
				} else {
					request.setAttribute("errors", errors);
					doGet(request, response);
				}

			} catch (Exception e){
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}

		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		GenericDao<DbLotEntity, Integer> flightDao = new GenericDao<DbLotEntity, Integer>(DbLotEntity.class);

		if (action == null) {

			HttpSession session = request.getSession();
			DbUzytkownikEntity user = (DbUzytkownikEntity) session.getAttribute("user");
			if(user.getRola().contentEquals("administrator")){
				request.setAttribute("flights", new FlightsDao().getAll());
			} else {
				request.setAttribute("flights", new FlightsDao().getAllByUser(user));
			}

			request.getRequestDispatcher("showFlights.jsp").forward(request, response);
			return;

		}
		if (action.equals("editorsave")) {

			request.setAttribute("lotniska", new DbLotniskoEntityDao().getAll());

			if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
				DbLotEntity lot = new FlightsDao().getById(Integer.parseInt(request.getParameter("id")));
				if(request.getAttribute("lot") == null)
					request.setAttribute("lot", lot);
			}

			request.getRequestDispatcher("addFlights.jsp").forward(request, response);
			return;
		}

		if(action.equals("deleteFlights")) {
			if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
				DbLotEntity lot = new FlightsDao().getById(Integer.parseInt(request.getParameter("id")));
				new FlightsDao().remove(lot);
				response.sendRedirect(request.getContextPath()+"/FlightController");
			}
		}

	}

	private boolean pola(HttpServletRequest request){
		String cenaStatyczna = request.getParameter("cenaStatyczna");
		String wylot = request.getParameter("wylot");
		if(cenaStatyczna==null ||cenaStatyczna.equals("")||wylot==null||wylot.equals(""))
			return false;
		return true;
	}
}