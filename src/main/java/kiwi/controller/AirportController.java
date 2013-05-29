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
import kiwi.dao.AirportDao;
import kiwi.model.Airport;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author bartek
 */
@WebServlet(name = "airportController", urlPatterns = {"/airportController"})
public class AirportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    	List<Airport> airports = new ArrayList<Airport>();
//    	airports.add(new Airport("Balice", "Poland", "Krakow"));
//    	airports.add(new Airport("Dubaj", "UAE", "Dubaj"));
    	List<Airport> airports = new AirportDao().getAll();
    	request.setAttribute("airports", airports);
    	request.getRequestDispatcher("/showAirports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  String action = request.getParameter("action");
          Airport airport = new Airport();
          try {
              BeanUtils.populate(airport, request.getParameterMap());
              if(airport.isFullyFilled()) { //sprawdzenie czy uzupełniono wszystkie pola, jeśli tak - dodaj do bazy
                   if(action.equals("add")) {
                      addAirport(airport);}
                   else if(action.equals("edit")) {
                	   request.setAttribute("airport", airport);
                	   request.getRequestDispatcher("/editAirport.jsp").forward(request, response);   
                   }
                   else if(action.equals("update"))
                	   updateAirport(airport);
                   else if(action.equals("delete"))
                	  deleteAirport(airport);
              }
              else // przekieruj do formularza spowrotem
                   request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
            	  //response.sendRedirect("/kiwi/WEB-INF/errorPage.jsp");
                 
          } catch (IllegalAccessException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InvocationTargetException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(AirportController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void addAirport(Airport airport) {
       AirportDao airportDao = new AirportDao();
       airportDao.create(airport);
    }
    
    private void updateAirport(Airport airport) {
       AirportDao airportDao = new AirportDao();
       airportDao.update(airport);
    }
    
    private void deleteAirport(Airport airport) {
        AirportDao airportDao = new AirportDao();
        airportDao.delete(airport);
     }
}
