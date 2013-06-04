package kiwi.controller;

import kiwi.dao.ClassDao;
import kiwi.dao.DbLotniskoEntityDao;
import kiwi.dao.FlightsDao;
import kiwi.models.DbLotEntity;
import kiwi.models.SearchForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Level.*;

/**
 * User: scroot
 * Date: 02.06.13
 * Time: 18:24
 */
@WebServlet("/search.html")
public class Search extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.setAttribute("foundFlights", null);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("flights", new DbLotniskoEntityDao().getAll());
		request.setAttribute("nazwyKlas", new ClassDao().getAll());

		if (request.getParameter("sent") != null && request.getParameter("sent").equals("1"))
		{
			SearchForm sf = new SearchForm();
			try
			{
				BeanUtils.populate(sf, request.getParameterMap());
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			Logger logger = Logger.getLogger(this.getClass());
			logger.log(INFO, sf.toString());


			if (!sf.validate())
			{
				request.setAttribute("errors", sf.getErrors());
			} else
			{
				List<List<DbLotEntity>> loty = null;
				if (sf.getDirect() != null)
				{
					DbLotEntity lot = new FlightsDao().findFlightsByAirportNames(sf);
					if (lot != null)
					{
						loty = new ArrayList<List<DbLotEntity>>();
						loty.add(new ArrayList<DbLotEntity>());
						loty.get(0).add(lot);
					}
				} else
					loty = new FlightsDao().findFlightsByAttributes(sf);

				Integer iloscMiejsc = sf.getIlosc()+sf.getIlosc_dz()+sf.getIlosc_inf();

				FlightsDao flightsDao = new FlightsDao();
				List<List<DbLotEntity>> remove = new ArrayList<List<DbLotEntity>>();
				for(List<DbLotEntity> i: loty) {
					for(DbLotEntity j: i) {
						int ilosc = flightsDao.getFreeSeatsCountInClass(j, sf.getKlasaDb());

						if(flightsDao.getFreeSeatsCountInClass(j, sf.getKlasaDb()) < iloscMiejsc) {
							remove.add(i); break;
						}
					}
				}
				for(List<DbLotEntity> i: remove) {
					loty.remove(i);
				}

				List<Float> prices = new ArrayList<>();
				for(List<DbLotEntity> i: loty) {
					prices.add(flightsDao.getFlightPrice(i, sf));
				}

				request.setAttribute("foundFlights", loty);
				session.setAttribute("foundFlightsPrices", prices);
			}
		}

		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
