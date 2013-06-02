package kiwi.controller;

import com.sun.net.httpserver.HttpServer;
import kiwi.dao.AirportDao;
import kiwi.dao.ClassDao;
import kiwi.dao.FlightsDao;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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
		String to;
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
			}
			if (to.isEmpty())
			{
				errors.put("to", "Pole lotnisko docelowe nie może być puste");
			}
			if (klasa == 0)
			{
				errors.put("klasa", "Musisz wybrać pole klasa");
			}
			if (ilosc == 0)
			{
				errors.put("ilosc", "Musisz podać ilośc pasażerów dorosłych");
			}
			if (ilosc_dz == 0)
			{
				errors.put("ilosc_dz", "Musisz podać ilość dzieci");
			}
			if (ilosc_inf == 0)
			{
				errors.put("ilosc_inf", "Musisz podać ilość infantów");
			}
			if (data.matches("\\d{2}/\\d{2}/\\d{4}"))
			{
				errors.put("data", "Musisz podać datę w formacie dd/mm/yyyy");
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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		request.setAttribute("flights", new AirportDao().getAll());
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
			if (!sf.validate())
			{
				request.setAttribute("errors", sf.getErrors());
			} else {
				Logger logger = Logger.getLogger(this.getClass());
				logger.log(Priority.ERROR, sf);
			}
		}

		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
