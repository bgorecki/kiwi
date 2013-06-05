<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
	<div style="float: left;">
		<form action="<c:url value="/airplanes"/>" class="block-content form" method="POST">       
	        <fieldset class="grey-bg">
	        <h1>Dodawanie samolotu</h1>
	        <legend>Informacje o samolocie</legend>
	        <p>
	        	<label for="d" class="required">(pola wymagane)</label>
		    	<label for="nazwa" class="required">Nazwa: </label>
	        	<input type="text" name="nazwa"/></br>
	        	<label for="wielkosc" class="required">Wielkość (rozpiętość skrzydeł) [m]: </label>
	            <input type="text" name="wielkosc"/></br>
	            <label for="waga" class="required">Waga [kg]:</label>
	            <input type="text" name="waga"/></br>
	            <label for="zuzyciePaliwa" class="required">Zużycie paliwa [l/h]:</label>
	            <input type="text" name="zuzyciePaliwa"/></br>
	            <input type="hidden" value="add" name="action"/>
	        </p>
	        <button type="submit">Dodaj</button>
	    </fieldset>
	    </form>
	</div>
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />