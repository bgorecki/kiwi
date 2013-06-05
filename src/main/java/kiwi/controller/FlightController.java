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
			if(!new FlightsDao().isFullyFilled(flight)){
				request.setAttribute("msg","srete");
				request.getRequestDispatcher("addFlights.jsp").forward(request,response);
			}
			DbLotEntity flights = new DbLotEntity();
			try {
				BeanUtils.populate(flights, request.getParameterMap());

				if(request.getParameter("id") == null) flightDao.create(flights);
				else flightDao.update(flights);

			} catch (Exception e){
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			response.sendRedirect("FlightController");
			return;
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

			if(request.getParameter("id") != null) {
				DbLotEntity lot = new FlightsDao().getById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("lot", lot);
			}

			request.getRequestDispatcher("addFlights.jsp").forward(request, response);
			return;
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