package kiwi.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.FlightsDao;
import kiwi.models.DbLotEntity;
import kiwi.models.DbPasazerEntity;
import kiwi.models.DbRekordyLotuEntity;
import kiwi.models.DbRezerwacjaEntity;
import kiwi.models.SearchForm;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/reservation.html")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("action") != null) {
			switch(request.getParameter("action")) {
			case "submit": 
				request.getRequestDispatcher("submitReservation.jsp").forward(request, response);
				return;
			case "submited":
				
				return;
			}
		}
		
		request.getRequestDispatcher("passengersPersonalDataForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String choosen = request.getParameter("choosen");
		SearchForm sf = new SearchForm();
		try {
			BeanUtils.populate(sf, request.getParameterMap());
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		} 
		sf.validate();

		String[] dateParts = sf.getData().split("/");
		Date dataRezerwacji = new Date(Integer.parseInt(dateParts[2])-1900, Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
		
		// Walidacja czy wszystkie pola są uzupełnione
		Iterator<String[]> it = request.getParameterMap().values().iterator();
		while(it.hasNext()) {
			for(String s : it.next()) {
				if(s.equals("")) {
					request.setAttribute("msg", "Wszystkie wymagane pola muszą być uzupełnione!");
					request.getRequestDispatcher("passengersPersonalDataForm.jsp").forward(request, response);
					return;
				}
			}
		}		
		
		List<DbPasazerEntity> pasazerowie = new LinkedList<DbPasazerEntity>();
		List<DbLotEntity> lotyRezerwacji = ((List<List<DbLotEntity>>) request.getSession().getAttribute("foundFlights")).get(Integer.valueOf(choosen));

		// Wyciagniecie pasazerow z formularza
		DbPasazerEntity pasazer;
		for(int i=0; i < Integer.valueOf(sf.getIlosc()); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko"+i));
			pasazer.setWiek("Adult");
			pasazerowie.add(pasazer);
		}
		for(int i=0; i < Integer.valueOf(sf.getIlosc_dz()); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie_dz"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko_dz"+i));
			pasazer.setWiek("Kid");
			pasazerowie.add(pasazer);
		}
		for(int i=0; i < Integer.valueOf(sf.getIlosc_inf()); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie_inf"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko_inf"+i));
			pasazer.setWiek("Infant");
			pasazerowie.add(pasazer);
		}
		
		DbRezerwacjaEntity rezerwacja = new DbRezerwacjaEntity();
		
		List<DbRekordyLotuEntity> rekordyLotuRezerwacji = new LinkedList<DbRekordyLotuEntity>();
		DbRekordyLotuEntity rekord;
		FlightsDao fdao = new FlightsDao();
		// Dla kazdego pasazera utworz rekordy lotów
		for(DbPasazerEntity p : pasazerowie) {
			p.setRezerwacjaByIdRezerw(rezerwacja);
			for(DbLotEntity l : lotyRezerwacji) {
				rekord = new DbRekordyLotuEntity();
				rekord.setRezerwacjaByIdRez(rezerwacja);
				rekord.setPasazerByIdOs(p);
				rekord.setLotByIdLot(l);
				rekord.setDataPrzylotu(dataRezerwacji);
				rekord.setDataWylotu(dataRezerwacji);
				rekord.setKlasaByIdKlas(sf.getKlasaDb());
				rekord.setCenaDynamiczna(fdao.getPrice(l, sf));
				rekordyLotuRezerwacji.add(rekord);
			}
		}
		
		rezerwacja.setRekordyLotusByIdRezerwacji(rekordyLotuRezerwacji);
		rezerwacja.setCenaCalkowita(fdao.getFlightPrice(lotyRezerwacji, sf));
		
		request.getSession().setAttribute("rezerwacjaWToku", rezerwacja);
		response.sendRedirect("reservation.html?action=submit");
	}

}
