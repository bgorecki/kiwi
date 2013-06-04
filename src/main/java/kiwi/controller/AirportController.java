package kiwi.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.DbLotniskoEntityDao;
import kiwi.dao.DbLotniskoEntityDao;
import kiwi.models.DbLotniskoEntity;
import kiwi.models.DbLotniskoEntity;

import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author bartek
 */
@WebServlet("/airports")
public class AirportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<DbLotniskoEntity> airports = new DbLotniskoEntityDao().getAll();
    	request.setAttribute("airports", airports);
    	request.getRequestDispatcher("/showAirports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.setCharacterEncoding("UTF-8");
		 String action = request.getParameter("action");
		 switch(action) { 
			 case "add": addAirport(request, response); break;
			 case "edit": editAirport(request, response); break;
			 case "update": updateAirport(request, response); break;
			 case "delete": deleteAirplane(request, response); break;
			 default: response.sendError(1, "Problem z parametrem action");
		 }
	}
	
	private void addAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLotniskoEntity airport = new DbLotniskoEntity();
		try {
			BeanUtils.populate(airport, request.getParameterMap());
			new DbLotniskoEntityDao().create(airport);
			doGet(request, response);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
   }
	
	private void editAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLotniskoEntity airport = new DbLotniskoEntityDao().read(Integer.parseInt(request.getParameter("idLotniska")));
		request.setAttribute("airport", airport);
   	request.getRequestDispatcher("/editAirport.jsp").forward(request, response);
   }
	
	private void updateAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLotniskoEntity airport = new DbLotniskoEntityDao().read(Integer.parseInt(request.getParameter("idLotniska")));
		try {
			BeanUtils.populate(airport, request.getParameterMap());
			new DbLotniskoEntityDao().update(airport);
			doGet(request, response);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
   }
	
	private void deleteAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLotniskoEntity airport = new DbLotniskoEntityDao().read(Integer.parseInt(request.getParameter("idLotniska")));
		new DbLotniskoEntityDao().delete(airport);
		doGet(request, response);
   }
}
