package kiwi.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kiwi.models.DbUzytkownikEntity;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// TODO Tak byle jak.. pomyśleć nad tym .. NIE WZOROWAĆ SIĘ NA TYM CZYMŚ :P
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || password == null || username.equals("") || password.equals("")) {
			request.setAttribute("msg", "Wszystkie pola muszą być uzupełnione!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		DbUzytkownikEntity userWhologgedIn = DbUzytkownikEntity.authenticate(username, password);
		
		if(userWhologgedIn == null ) {
			request.setAttribute("msg", "Podany użytkownik nie istnieje lub błędne hasło!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		HttpSession httpSession = request.getSession(true);
		userWhologgedIn.setOstatnieLogowanie(new Timestamp(new java.util.Date().getTime()));
		httpSession.setAttribute("user", userWhologgedIn);
		
		response.sendRedirect("index.jsp");
	}

}
