package kiwi.controller;

import kiwi.dao.FlightsDao;
import kiwi.models.DbLotEntity;
import kiwi.models.DbUzytkownikEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: scroot
 * Date: 05.06.13
 * Time: 00:25
 */
@WebServlet("/rentownosc.html")
public class Rentownosc extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DbLotEntity lot = new FlightsDao().getById(Integer.parseInt(request.getParameter("id")));

	}

}
