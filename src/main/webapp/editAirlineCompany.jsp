<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />
		
		<div style="float: left">
	    <form method="post" id="simple_form" class="block-content form" action="airlinecompanies?action=editCompany&id=${airlineCompany.idPrzewoznika}">
	    	<c:if test="${not empty msg1}" >
	    		<ul class="message error no-margin"><li>${msg1}</li></ul>
	    	</c:if>
	    	<fieldset>
	    		<h1>Edytuj przewoźnika</h1>
	        	<legend>Informacje o przewoźniku</legend>
	        	<p>
	        		<label for="field" class="required">Wymagane pola</label>
	        	</p>
	        	<p>
			        <label for="fieldNazwa" class="required">Nazwa</label>
			        <input type="text" maxlength="150" name="nazwa" id="fieldNazwa" value="${airlineCompany.nazwa}">
			        <label for="fieldKraj" class="required">Kraj</label>
			        <input type="text" maxlength="150" name="kraj" id="fieldKraj" value="${airlineCompany.kraj}">
			    	<br/><button type="submit">Edytuj</button>
			    </p>
			   
	    	</fieldset>
	    </form>
	    </div>
	    <div style="margin-left: 50px; float: left">
	    <form method="post" id="simple_form" class="block-content form" action="airlinecompanies?action=addCompanyUser&companyId=${airlineCompany.idPrzewoznika}">
	    	<c:if test="${not empty msg2}" >
	    		<ul class="message error no-margin"><li>${msg2}</li></ul>
	    	</c:if>
	    	<fieldset>
	    		<h1>Dodaj użytkownika</h1>
	        	<legend>Informacje o użytkowniku</legend>
	        	<p>
	        		<label for="field" class="required">Wymagane pola</label>
	        	</p>
	        	<p>
			        <label for="fieldLogin" class="required">Login administratora przewoźnika</label>
			        <input type="text" maxlength="150" name="login" id="fieldLogin"  value="${param.login}">
			        <label for="fieldHaslo" class="required">Hasło administratora przewoźnika</label>
			        <input type="password" maxlength="150" name="haslo" id="fieldHaslo">
			        <label for="fieldHasloPowtorzone" class="required">Powtórz hasło administratora przewoźnika</label>
			        <input type="password" maxlength="150" name="hasloPowtorzone" id="fieldHasloPowtorzone">
			    	<br/><button type="submit">Dodaj</button>
			    </p>
			   
	    	</fieldset>
	 	</form>
	    </div>
	    <div style="padding-top: 50px; padding-bottom: 50px; clear: both">
	    <h1>Użytkownicy przewoźnika z dostępem do systemu</h1>
	    <table class="table" cellspacing="0">
 
	    <thead>
	        <tr>
	            <!-- This is a special cell for loading statuses - see below for more -->
	            <th class="black-cell"><span class="success"></span></th>
	             
	            <th scope="col">Login</th>
	            <th scope="col">Ostatnie logowanie</th>
	            <th scope="col" class="table-actions">Akcje</th>
	        </tr>
	    </thead>
 
	    <tfoot>
	        <tr>
	            <td colspan="4"><img src="images/icons/fugue/arrow-curve-000-left.png" width="16" height="16" class="picto"> <b>Znalezione rekordy:</b> ${ empty airlineCompany.uzytkowniksByIdPrzewoznika ? 0 : airlineCompany.uzytkowniksByIdPrzewoznika.size()} </td>
	        </tr>
	    </tfoot>
     
	    <tbody>
	        
	        <c:forEach var="u" items="${airlineCompany.uzytkowniksByIdPrzewoznika}" varStatus="status">
	        	<tr>
		            <th scope="row" class="table-check-cell"></th>
		            <td>${u.login}</td>
		            <td>${u.ostatnieLogowanie}</td>
		             
		            <!-- The class table-actions is designed for action icons -->
		            <td class="table-actions">
		                <a href="<c:url value="/airlinecompanies?action=deleteCompanyUser&id=${u.idUzytkownika}"/>" title="Usuń" class="with-tip"><img src="images/icons/fugue/cross-circle.png" width="16" height="16"></a>
		            </td>
	        	</tr>
	        </c:forEach>
	         
	    </tbody>
 
		</table>
	</div>
	    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />
	
