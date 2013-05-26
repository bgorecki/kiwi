package kiwi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.UserDao;
import kiwi.model.UserPrzewoznik;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NA RAZIE TYLKO TEST HIBERNATE
		UserPrzewoznik user = new UserPrzewoznik();
		user.setPassword("hasło");
		user.setUsername("użytszkodnik:)");
		new UserDao().create(user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
