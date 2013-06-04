<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />
		<div style="float: left">
	    <form method="post" id="simple_form" class="block-content form" action="airlinecompanies?action=addCompany">
	    	<c:if test="${not empty msg}" >
	    		<ul class="message error no-margin"><li>${msg}</li></ul>
	    	</c:if>
	    	<fieldset>
	    		<h1>Dodaj przewoźnika</h1>
	    		<p>
	        		<label for="field" class="required">Wymagane pola</label>
	        	</p>
	        	<legend>Informacje o przewoźniku</legend>
	        	<p>
			        <label for="fieldNazwa" class="required">Nazwa</label>
			        <input type="text" maxlength="150" name="nazwa" id="fieldNazwa" value="${param.nazwa}">
			        <label for="fieldKraj" class="required">Kraj</label>
			        <input type="text" maxlength="150" name="kraj" id="fieldKraj" value="${param.kraj}">
			    </p>
			    <legend>Administrator przewoźnika</legend><br/>
			    <p>
			        <label for="fieldLogin" class="required">Login</label>
			        <input type="text" maxlength="150" name="login" id="fieldLogin"  value="${param.login}">
			        <label for="fieldHaslo" class="required">Hasło</label>
			        <input type="password" maxlength="150" name="haslo" id="fieldHaslo">
			        <label for="fieldHasloPowtorzone" class="required">Powtórz hasło</label>
			        <input type="password" maxlength="150" name="hasloPowtorzone" id="fieldHasloPowtorzone">
			    </p>
			    <p>
			        <button type="submit">Dodaj</button>
			    </p>
	    	</fieldset>
	    </form>
	    </div>
	    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />
	
