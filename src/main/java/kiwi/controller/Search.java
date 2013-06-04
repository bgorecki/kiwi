package kiwi.controller;

import kiwi.dao.ClassDao;
import kiwi.dao.DbKlasaDao;
import kiwi.dao.DbLotniskoEntityDao;
import kiwi.dao.FlightsDao;
import kiwi.models.DbKlasaEntity;
import kiwi.models.DbLotEntity;
import kiwi.models.DbLotniskoEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.apache.log4j.Level.*;

/**
 * User: scroot
 * Date: 02.06.13
 * Time: 18:24
 */
@WebServlet("/search.html")
public class Search extends HttpServlet
{

	public class SearchForm
	{

		String from;
		DbLotniskoEntity fromLotnisko;
		String to;
		DbLotniskoEntity toLotnisko;
		DbKlasaEntity klasaDb;
		Integer klasa;
		Integer ilosc;
		Integer ilosc_dz;
		Integer ilosc_inf;
		String data;
		Boolean direct;
		Map<String, String> errors = new HashMap<String, String>();

		public Boolean validate()
		{
			if (from.isEmpty())
			{
				errors.put("from", "Pole lotnisko początkowe nie może być puste");
			} else
			{
				try
				{
					String[] fromEx = from.split(" - ");
					DbLotniskoEntityDao lotnisko = new DbLotniskoEntityDao();
					fromLotnisko = lotnisko.getByName(fromEx[1]);
				} catch (Exception e)
				{
					errors.put("from", "Nieprawidłowe lotnisko");
				}
			}

			if (to.isEmpty())
			{
				errors.put("to", "Pole lotnisko docelowe nie może być puste");
			} else
			{
				try
				{
					String[] fromEx = to.split(" - ");
					DbLotniskoEntityDao lotnisko = new DbLotniskoEntityDao();
					toLotnisko = lotnisko.getByName(fromEx[1]);
				} catch (Exception e)
				{
					errors.put("to", "Nieprawidłowe lotnisko");
				}
			}
			if (klasa == null)
			{
				errors.put("klasa", "Musisz wybrać pole klasa");
			} else {
				klasaDb = new DbKlasaDao().getById(klasa);
			}
			if (ilosc == null)
			{
				errors.put("ilosc", "Musisz podać ilośc pasażerów dorosłych");
			}
			if (ilosc_dz == null)
			{
				errors.put("ilosc_dz", "Musisz podać ilość dzieci");
			}
			if (ilosc_inf == null)
			{
				errors.put("ilosc_inf", "Musisz podać ilość infantów");
			}
			if (!data.matches("\\d{2}/\\d{2}/\\d{4}"))
			{
				errors.put("data", "Musisz podać datę w formacie dd/mm/yyyy");
			}

			if (ilosc + ilosc_dz + ilosc_inf > 12)
			{
				errors.put("ilosc", "Jedna rezerwacja może obejmować maksymalnie 12 pasażerów");
			}

			if (errors.isEmpty()) return true;
			return false;
		}

		public String getFrom()
		{
			return from;
		}

		public void setFrom(String from)
		{
			this.from = from;
		}

		public String getTo()
		{
			return to;
		}

		public void setTo(String to)
		{
			this.to = to;
		}

		public Integer getKlasa()
		{
			return klasa;
		}

		public void setKlasa(Integer klasa)
		{
			this.klasa = klasa;
		}

		public Integer getIlosc()
		{
			return ilosc;
		}

		public void setIlosc(Integer ilosc)
		{
			this.ilosc = ilosc;
		}

		public Integer getIlosc_dz()
		{
			return ilosc_dz;
		}

		public void setIlosc_dz(Integer ilosc_dz)
		{
			this.ilosc_dz = ilosc_dz;
		}

		public Integer getIlosc_inf()
		{
			return ilosc_inf;
		}

		public void setIlosc_inf(Integer ilosc_inf)
		{
			this.ilosc_inf = ilosc_inf;
		}

		public String getData()
		{
			return data;
		}

		public void setData(String data)
		{
			this.data = data;
		}

		public Boolean getDirect()
		{
			return direct;
		}

		public void setDirect(Boolean direct)
		{
			this.direct = direct;
		}

		public Map<String, String> getErrors()
		{
			return errors;
		}

		public void setErrors(Map<String, String> errors)
		{
			this.errors = errors;
		}

		@Override
		public String toString()
		{
			return "SearchForm{" +
					       "from='" + from + '\'' +
					       ", to='" + to + '\'' +
					       ", klasa=" + klasa +
					       ", ilosc=" + ilosc +
					       ", ilosc_dz=" + ilosc_dz +
					       ", ilosc_inf=" + ilosc_inf +
					       ", data='" + data + '\'' +
					       ", direct=" + direct +
					       ", errors=" + errors +
					       '}';
		}

		public DbLotniskoEntity getFromLotnisko()
		{
			return fromLotnisko;
		}

		public DbLotniskoEntity getToLotnisko()
		{
			return toLotnisko;
		}

		public DbKlasaEntity getKlasaDb()
		{
			return klasaDb;
		}
	}

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
						int ilosc = flightsDao.getFreeSeatsCount(j, sf.getKlasaDb());

						if(flightsDao.getFreeSeatsCount(j, sf.getKlasaDb()) < iloscMiejsc) {
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
