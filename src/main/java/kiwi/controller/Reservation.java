package kiwi.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String choosen = request.getParameter("choosen");
		String ilosc = request.getParameter("ilosc");
		String ilosc_dz = request.getParameter("ilosc_dz");
		String ilosc_inf = request.getParameter("ilosc_inf");
		String klasa = request.getParameter("klasa");
		
		// Wyciagniecie pasazerow z formularza
		List<DbPasazerEntity> pasazerowie = new LinkedList<DbPasazerEntity>();
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
		
		List<List<DbLotEntity>> loty = (List<List<DbLotEntity>>) request.getSession().getAttribute("foundFlights");
		List<DbLotEntity> lotyRezerwacji = loty.get(Integer.valueOf(choosen));
		
		DbRezerwacjaEntity rezerwacja = new DbRezerwacjaEntity();
		rezerwacja.setPasazersByIdRezerwacji(pasazerowie);
		
		
		List<DbRekordyLotuEntity> rekordyLotuRezerwacji = new LinkedList<DbRekordyLotuEntity>();
		DbRekordyLotuEntity rekord;
		// Dla kazdego pasazera utworz rekordy lotów
		for(DbPasazerEntity p : pasazerowie) {
			for(DbLotEntity l : lotyRezerwacji) {
				rekord = new DbRekordyLotuEntity();
				rekord.setRezerwacjaByIdRez(rezerwacja);
				rekord.setPasazerByIdOs(p);
				rekord.setLotByIdLot(l);
			}
		}

	}

}
