package kiwi.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.DbKlasaDao;
import kiwi.dao.FlightsDao;
import kiwi.dao.GenericDao;
import kiwi.models.DbKlasaEntity;
import kiwi.models.DbLotEntity;
import kiwi.models.DbPasazerEntity;
import kiwi.models.DbRekordyLotuEntity;
import kiwi.models.DbRezerwacjaEntity;

@WebServlet("/reservation.html")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("passengersPersonalDataForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String choosen = request.getParameter("choosen");
		String ilosc = request.getParameter("ilosc");
		String ilosc_dz = request.getParameter("ilosc_dz");
		String ilosc_inf = request.getParameter("ilosc_inf");
		String klasa = request.getParameter("klasa");
		String data = request.getParameter("data");
		String[] dateParts = data.split("/");
		Date dataRezerwacji = new Date(Integer.parseInt(dateParts[2])-1900, Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
		
		DbKlasaEntity klasaDb = new DbKlasaDao().getById(Integer.parseInt(klasa));
		
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
		Float cenaRezerwacji = ((List<Float>) request.getSession().getAttribute("foundFlightsPrices")).get(Integer.valueOf(choosen));
		
		// Wyciagniecie pasazerow z formularza
		DbPasazerEntity pasazer;
		for(int i=0; i < Integer.valueOf(ilosc); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko"+i));
			pasazer.setWiek("dorosly");
			pasazerowie.add(pasazer);
		}
		for(int i=0; i < Integer.valueOf(ilosc_dz); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie_dz"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko_dz"+i));
			pasazer.setWiek("dziecko");
			pasazerowie.add(pasazer);
		}
		for(int i=0; i < Integer.valueOf(ilosc_inf); i++) {
			pasazer = new DbPasazerEntity();
			pasazer.setImie(request.getParameter("imie_inf"+i));
			pasazer.setNazwisko(request.getParameter("nazwisko_inf"+i));
			pasazer.setWiek("infant");
			pasazerowie.add(pasazer);
		}
		
		DbRezerwacjaEntity rezerwacja = new DbRezerwacjaEntity();
		
		List<DbRekordyLotuEntity> rekordyLotuRezerwacji = new LinkedList<DbRekordyLotuEntity>();
		DbRekordyLotuEntity rekord;
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
				rekord.setKlasaByIdKlas(klasaDb);
				rekord.setCenaDynamiczna(0.0f);
				rekordyLotuRezerwacji.add(rekord);
			}
		}
		
		rezerwacja.setRekordyLotusByIdRezerwacji(rekordyLotuRezerwacji);
		rezerwacja.setCenaCalkowita(cenaRezerwacji);
		
		// TODO Jeszcze nie powinno się dodawać do bazy ale test
		GenericDao<DbRezerwacjaEntity, Integer> daoTest = new GenericDao<>(DbRezerwacjaEntity.class);
		daoTest.create(rezerwacja);
		
		request.getSession().setAttribute("rezerwacjaWToku", rezerwacja);
		
	}

}
