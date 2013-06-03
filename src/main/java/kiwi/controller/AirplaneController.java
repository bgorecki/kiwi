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

import org.apache.commons.beanutils.BeanUtils;

import kiwi.dao.DbSamolotEntityDao;
import kiwi.dao.DbLotniskoEntityDao;
import kiwi.models.DbSamolotEntity;

@WebServlet("/AirplaneController")
public class AirplaneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AirplaneController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<DbSamolotEntity> airplanes = new DbSamolotEntityDao().getAll();
    	request.setAttribute("airplanes", airplanes);
    	request.getRequestDispatcher("/showAirplanes.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
		 DbSamolotEntity airplane = new DbSamolotEntity();
         try {
             BeanUtils.populate(airplane, request.getParameterMap());
             if(new DbSamolotEntityDao().isFullyFilled(airplane)) { //sprawdzenie czy uzupełniono wszystkie pola, jeśli tak - dodaj do bazy
                  if(action.equals("add")) {
                     addAirplane(airplane);}
                  else if(action.equals("edit")) {
               	   request.setAttribute("airplane", airplane);
               	   request.getRequestDispatcher("/editAirplane.jsp").forward(request, response);   
                  }
                  else if(action.equals("update"))
               	   updateAirplane(airplane);
                  else if(action.equals("delete"))
               	  deleteAirplane(airplane);
             }
             else // przekieruj do formularza spowrotem
                  request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
                
         } catch (IllegalAccessException ex) {
             Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InvocationTargetException ex) {
             Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
         }
	}
	
	private void addAirplane(DbSamolotEntity aiplane) {
			new DbSamolotEntityDao().create(aiplane);
	    }
	    
	    private void updateAirplane(DbSamolotEntity aiplane) {
	    	new DbSamolotEntityDao().update(aiplane);
	    }
	    
	    private void deleteAirplane(DbSamolotEntity aiplane) {
	    	new DbSamolotEntityDao().delete(aiplane);
	     }

}
