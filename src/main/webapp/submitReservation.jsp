<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />
		<div class="block-content">
	    <form method="get" id="simple_form" class="block-content form" action="">
	    	<fieldset>
	    		<h1>Twoja rezerwacja</h1>
	    		<c:choose>
	    		<c:when test="${not empty param.cancelled }">
	    			<h3>Anulowano rezerwację!</h3><br/><br/>
	    		</c:when>
	    		<c:when test="${not empty param.id and not empty param.kod }">
	    			<center>
	    				<h3>Zarezerwowano bilety pomyślnie!</h3><br/><br/>
	    				<h5>ID rezerwacji: ${param.id}</h5>
	    				<h5>Kod autoryzacyjny: ${param.kod }</h5><br/>
	    				Zapisz powyższe dane w bezpiecznym miejscu.
	    			</center>
	    		</c:when>
	    		<c:otherwise>
		    		<p>
		    			<h3>Do zapłaty: </h3><h2>${sessionScope.rezerwacjaWToku.cenaCalkowita}</h2>
		        		<h3>Pasażerowie:</h3>
		        			<c:forEach var="p" items="${sessionScope.rezerwacjaWToku.pasazersByIdRezerwacji}">
		        				<span style="font-size: medium">- ${p.imie} ${p.nazwisko}</span><br/>
		        			</c:forEach>
		        	</p>
				    <p>
				    	<div class="block-border" style="float:left;">
				    		<h2><a href="reservation.html?action=canceled">Anuluj</a></h2>
				    	</div>
				   		<div class="block-border" style="float: right;">
				        	<h2><a href="reservation.html?action=submited">Potwierdź</a></h2>
				       	</div>
				    </p>
	    		</c:otherwise>
	    		</c:choose>
	    		 
	    		
	    	</fieldset>
	    </form>
	    </div>
	    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />
	
