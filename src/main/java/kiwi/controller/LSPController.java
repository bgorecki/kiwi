package kiwi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kiwi.dao.DbLSPDao;
import kiwi.dao.DbPracownikEntityDao;
import kiwi.dao.DbSamolotEntityDao;
import kiwi.models.DbLspEntity;
import kiwi.models.DbPracownikEntity;
import kiwi.models.DbSamolotEntity;

@WebServlet("/lsp")
public class LSPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LSPController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DbLspEntity> lsp = new DbLSPDao().getAll();
		request.setAttribute("lsp", lsp);
		request.getRequestDispatcher("/showLSP.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
			case "editAirplane": editAirplane(request, response); break;
			case "updateAirplane": updateAirplane(request, response); break;
			case "addLsp": addLsp(request, response); break;
			case "addEmployee": addEmployee(request, response); break;
			case "editEmployee": editEmployee(request, response); break;
			case "updateEmployee": updateEmployee(request, response); break;
			case "delete": deleteLSP(request, response); break;
		}
	}

	private void editAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DbSamolotEntity> airplanes = new DbSamolotEntityDao().getAll();
		request.setAttribute("airplanes", airplanes);
		request.setAttribute("idLsp", request.getParameter("idLsp"));
		request.setAttribute("lspIdSamolotu", request.getParameter("idSamolotu"));
		request.getRequestDispatcher("/lspEditAirplane.jsp").forward(request, response);
	}
	
	private void updateAirplane(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLspEntity lsp = new DbLSPDao().read(Integer.valueOf(request.getParameter("idLsp")));
		DbSamolotEntity airplane = new DbSamolotEntityDao().read(Integer.valueOf(request.getParameter("idSamolotu")));
		lsp.setSamolotByIdSam(airplane);
		new DbLSPDao().update(lsp);
		doGet(request, response);
	}
	
	private void addLsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DbPracownikEntity> employees = new DbPracownikEntityDao().getAll();
		request.setAttribute("employees", employees);
		request.setAttribute("idLsp", request.getParameter("idLsp"));
		request.getRequestDispatcher("/lspAddEmployee.jsp").forward(request, response);
	}
	
	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLspEntity lsp = new DbLSPDao().read(Integer.valueOf(request.getParameter("idLsp")));
		DbPracownikEntity employee = new DbPracownikEntityDao().read(Integer.valueOf(request.getParameter("idPracownika")));
		lsp.setPracownikByIdPrac(employee);
		new DbLSPDao().create(lsp);
		doGet(request, response);
	}
	
	private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DbPracownikEntity> employees = new DbPracownikEntityDao().getAll();
		request.setAttribute("employees", employees);
		request.setAttribute("idLsp", request.getParameter("idLsp"));
		request.setAttribute("idPracownika", request.getParameter("idPracownika"));
		request.getRequestDispatcher("/lspEditEmployee.jsp").forward(request, response);
	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLspEntity lsp = new DbLSPDao().read(Integer.valueOf(request.getParameter("idLsp")));
		DbPracownikEntity employee = new DbPracownikEntityDao().read(Integer.valueOf(request.getParameter("idPracownika")));
		lsp.setPracownikByIdPrac(employee);
		new DbLSPDao().update(lsp);
		doGet(request, response);
	}
	
	private void deleteLSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbLspEntity lsp = new DbLSPDao().read(Integer.valueOf(request.getParameter("idLSP")));
		new DbLSPDao().delete(lsp);
		doGet(request, response);
	}
}
