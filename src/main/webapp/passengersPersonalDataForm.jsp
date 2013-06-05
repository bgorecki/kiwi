<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />
		<div style="float: left">
	    <form method="post" id="simple_form" class="block-content form" action="reservation.html">
	    	<c:if test="${not empty msg}" >
	    		<ul class="message error no-margin"><li>${msg}</li></ul>
	    	</c:if>
	    	<fieldset>
	    	
	    		<!-- A tutaj trzymam sobie dane potrzebne w dalejszej obrobce w doPost -->
	    		<input type="hidden" name="from" value="${param.from}" />
	    		<input type="hidden" name="to" value="${param.to}" />
	    		<input type="hidden" name="ilosc" value="${param.ilosc}" />
	    		<input type="hidden" name="ilosc_dz" value="${param.ilosc_dz}" />
	    		<input type="hidden" name="ilosc_inf" value="${param.ilosc_inf}" />
	    		<input type="hidden" name="klasa" value="${param.klasa}" />
	    		<input type="hidden" name="choosen" value="${param.choosen}" />
	    		<input type="hidden" name="data" value="${param.data}" />
	    	
	    		<h1>Dane pasażerów</h1>
	    		<p>
	        		<label for="field" class="required">Wszystkie pola są wymagane</label>
	        	</p>
	        	<c:if test="${param.ilosc > 0 }">
	        	<legend>Dorośli</legend>
	        	<p>
	        		<c:forEach var="i" begin="0" end="${(param.ilosc) - 1}">
	        			<c:set var="imieAsString">imie${i}</c:set>
	        			<c:set var="nazwiskoAsString">nazwisko${i}</c:set>
	        			Imię: <input type="text" name="imie${i}" value="${param[imieAsString]}"/>
	        			Nazwisko: <input type="text" name="nazwisko${i}" value="${param[nazwiskoAsString]}" />
	        			<br/>
	        		</c:forEach>
			    </p>
			    </c:if>
			    <c:if test="${param.ilosc_dz > 0 }">
			    <legend>Dzieci</legend>
	        	<p>
	        		<c:forEach var="i" begin="0" end="${(param.ilosc_dz) - 1}">
	        			<c:set var="imieAsString">imie_dz${i}</c:set>
	        			<c:set var="nazwiskoAsString">nazwisko_dz${i}</c:set>
	        			Imię: <input type="text" name="imie_dz${i}" value="${param[imie_dzAsString]}" />
	        			Nazwisko: <input type="text" name="nazwisko_dz${i}" value="${param[nazwisko_dzAsString]}" />
	        			<br/>
	        		</c:forEach>
	        		
			    </p>
			    </c:if>
			    <c:if test="${param.ilosc_inf > 0 }">
			    <legend>Infanty</legend>
	        	<p>
	        		<c:forEach var="i" begin="0" end="${(param.ilosc_inf) - 1}">
	        			<c:set var="imie_infAsString">imie_inf${i}</c:set>
	        			<c:set var="nazwisko_infAsString">nazwisko_inf${i}</c:set>
	        			Imię: <input type="text" name="imie_inf${i}" value="${param[imie_infAsString]}"/>
	        			Nazwisko: <input type="text" name="nazwisko_inf${i}" value="${param[nazwisko_infAsString]}" />
	        			<br/>
	        		</c:forEach>
			    </p>
			    </c:if>
			    <p>
			        <button type="submit">Dalej</button>
			    </p>
	    	</fieldset>
	    </form>
	    </div>
	    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />
	
