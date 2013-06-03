package kiwi.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kiwi.dao.DbLotniskoEntityDao;
import kiwi.dao.GenericDao;
import kiwi.models.DbLotniskoEntity;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.impl.DbAuth;

/**
 *
 * @author bartek
 */
@WebServlet(name = "airportController", urlPatterns = {"/airportController"})
public class AirportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<DbLotniskoEntity> airports = new DbLotniskoEntityDao().getAll();
    	request.setAttribute("airports", airports);
    	request.getRequestDispatcher("/showAirports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  request.setCharacterEncoding("UTF-8");
    	  String action = request.getParameter("action");
    	  DbLotniskoEntity airport = new DbLotniskoEntity();
          try {
              BeanUtils.populate(airport, request.getParameterMap());
              if(new DbLotniskoEntityDao().isFullyFilled(airport)) {//sprawdzenie czy uzupełniono wszystkie pola, jeśli tak - wykonaj akcje
                   if(action.equals("add")) {
                      addAirport(airport, request, response);}
                   else if(action.equals("edit")) {
                	   request.setAttribute("airport", airport);
                	   request.getRequestDispatcher("/editAirport.jsp").forward(request, response);   
                   }
                   else if(action.equals("update"))
                	   updateAirport(airport, request, response);
                   else if(action.equals("delete"))
                	  deleteAirport(airport, request, response);
              }
              else // przekieruj do formularza spowrotem
                  // poprawić 
            	  request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
                 
          } catch (IllegalAccessException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InvocationTargetException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    private void addAirport(DbLotniskoEntity airport, HttpServletRequest request, HttpServletResponse response) {
    	new DbLotniskoEntityDao().create(airport);
    	try {
			doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void updateAirport(DbLotniskoEntity airport, HttpServletRequest request, HttpServletResponse response) {
       new DbLotniskoEntityDao().update(airport);
       try {
		doGet(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
    
    private void deleteAirport(DbLotniskoEntity airport, HttpServletRequest request, HttpServletResponse response) {
    	new DbLotniskoEntityDao().delete(airport.getIdLotniska());
        try {
			doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
}
