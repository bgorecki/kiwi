<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />

	    <form method="post" class="form" action="airlinecompanies?action=addCompany">
	    	<span style="color:red;">${msg}</span><br/><br/>
	    	<fieldset>
	        	<legend>Dodaj przewoźnika</legend>
	        	<p>
	        		<label for="field" class="required">Wymagane pola</label>
	        	</p>
	        	<p>
			        <label for="fieldNazwa" class="required">Nazwa</label>
			        <input type="text" maxlength="150" name="nazwa" id="fieldNazwa" value="${param.nazwa}">
			        <label for="fieldKraj" class="required">Kraj</label>
			        <input type="text" maxlength="150" name="kraj" id="fieldKraj" value="${param.kraj}">
			        <br/><br/><br/>
			        <label for="fieldLogin" class="required">Login administratora przewoźnika</label>
			        <input type="text" maxlength="150" name="login" id="fieldLogin"  value="${param.login}">
			        <label for="fieldHaslo" class="required">Hasło administratora przewoźnika</label>
			        <input type="password" maxlength="150" name="haslo" id="fieldHaslo">
			        <label for="fieldHasloPowtorzone" class="required">Powtórz hasło administratora przewoźnika</label>
			        <input type="password" maxlength="150" name="hasloPowtorzone" id="fieldHasloPowtorzone">
			    </p>
			    <p>
			        <input type="submit" value="Dodaj">
			    </p>
	    	</fieldset>
	    </form>
	    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />
	
