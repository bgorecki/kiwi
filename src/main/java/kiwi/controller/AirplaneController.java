package kiwi.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kiwi.dao.DbSamolotEntityDao;
import kiwi.models.DbSamolotEntity;
import kiwi.models.DbUzytkownikEntity;

@WebServlet("/airplanes")
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
		 request.setCharacterEncoding("UTF-8");
		 String action = request.getParameter("action");
		 switch(action) { 
			 case "add": addAirplane(request, response); break;
			 case "edit": editAirplane(request, response); break;
			 case "update": updateAirplane(request, response); break;
			 case "delete": deleteAirplane(request, response); break;
			 default: response.sendError(1, "Problem z parametrem action");
		 }
	}
	
	private void addAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbSamolotEntity airplane = new DbSamolotEntity();
		try {
			BeanUtils.populate(airplane, request.getParameterMap());
			DbUzytkownikEntity user = (DbUzytkownikEntity)request.getSession().getAttribute("user");
			airplane.setPrzewoznikByIdPrzew(user.getPrzewoznikByIdPrzewoznika());
			new DbSamolotEntityDao().create(airplane);
			doGet(request, response);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }
	
	private void editAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbSamolotEntity airplane = new DbSamolotEntityDao().read(Integer.parseInt(request.getParameter("idSamolotu")));
		request.setAttribute("airplane", airplane);
    	request.getRequestDispatcher("/editAirplane.jsp").forward(request, response);
    }
	
	private void updateAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbSamolotEntity airplane = new DbSamolotEntityDao().read(Integer.parseInt(request.getParameter("idSamolotu")));
		try {
			BeanUtils.populate(airplane, request.getParameterMap());
			new DbSamolotEntityDao().update(airplane);
			doGet(request, response);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }
	
	private void deleteAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbSamolotEntity airplane = new DbSamolotEntityDao().read(Integer.parseInt(request.getParameter("idSamolotu")));
		new DbSamolotEntityDao().delete(airplane);
		doGet(request, response);
    }
}
