package kiwi.controller;

import kiwi.dao.FlightsDao;
import kiwi.models.DbLotEntity;
import kiwi.models.DbUzytkownikEntity;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
		FlightsDao fd = new FlightsDao();
		DbLotEntity lot = fd.getById(Integer.parseInt(request.getParameter("id")));
		Integer dzien = lot.getDzienTygodnia();
		dzien-=1;
		DateTime today = DateTime.now();

		Integer month = today.getMonthOfYear();
		Integer year = today.getYear();

		if(request.getParameter("month") != null) month = Integer.parseInt(request.getParameter("month"));
		if(request.getParameter("year") != null) year = Integer.parseInt(request.getParameter("year"));

		Integer firstDay = 1;
		DateTime date = new DateTime(year, month, firstDay, 0, 0);
		while(date.getDayOfWeek() == dzien) {
			date = date.plusDays(1); firstDay++;
		}

		Integer max = fd.getSeatsCount(lot);
		List<Integer> days = new ArrayList<>();
		//days.add(firstDay);
		Double rent = 0D;
		HashMap<Integer, Integer> ilosc = new LinkedHashMap<>();
		while(date.getMonthOfYear() == month) {
			days.add(date.getDayOfMonth());
			Integer ilosctmp = fd.getReservationsCountForDate(lot, new Date(date.getMillis()));
			ilosc.put(date.getDayOfMonth(), ilosctmp);
			rent += ilosctmp.doubleValue() / max.doubleValue();
			date = date.plusDays(7);
		}

		rent /= days.size();
		rent *= 100;

		request.setAttribute("days", days);
		request.setAttribute("counter", ilosc);
		request.setAttribute("max", max);
		request.setAttribute("lot", lot);
		request.setAttribute("rent", rent);

		request.getRequestDispatcher("rentownosc.jsp").forward(request, response);
	}

}

