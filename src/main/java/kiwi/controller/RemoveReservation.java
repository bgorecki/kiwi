package kiwi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.RezerwacjaDao;
import kiwi.models.DbRezerwacjaEntity;
import kiwi.utils.DatabaseConnector;


@WebServlet("/removereservation")
public class RemoveReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action") != null) {
			DatabaseConnector.getInstance().getSession().beginTransaction();
			new RezerwacjaDao().delete((DbRezerwacjaEntity)request.getSession().getAttribute("r"));
			DatabaseConnector.getInstance().getSession().getTransaction().commit();
			response.sendRedirect("index.jsp");
			return;
		}
		
		request.getRequestDispatcher("loginReservation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || password == null || username.equals("") || password.equals("")) {
			request.setAttribute("msg", "Wszystkie pola muszą być uzupełnione!");
			request.getRequestDispatcher("loginReservation.jsp").forward(request, response);
			return;
		}
		
		DbRezerwacjaEntity userWhologgedIn = new RezerwacjaDao().getByIdAndKod(Integer.parseInt(username), password);
		
		if(userWhologgedIn == null ) {
			request.setAttribute("msg", "Podana rezerwacja nie istnieje lub błędny kod!");
			request.getRequestDispatcher("loginReservation.jsp").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("r", userWhologgedIn);
		request.getRequestDispatcher("reservationRemoveSubmit.jsp").forward(request, response);
		return;
	}

}
