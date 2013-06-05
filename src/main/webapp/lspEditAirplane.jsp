<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
<form action="<c:url value="/lsp"/>" class="block-content form" method="POST">       
        <fieldset class="grey-bg">
        <h1>Edycja samolotu w locie</h1>
        <legend>Wybierz samolot - lspIdSamolotu = ${lspIdSamolotu }</legend>
        <p>
        	<select name="idSamolotu">
        		<c:forEach var="airplane" items="${airplanes }">
	        		<c:choose>
	        			<c:when test="${airplane.idSamolotu == lspIdSamolotu } ">
	        				<option selected="true" value="${airplane.idSamolotu }">${airplane.nazwa }</option>
	        			</c:when>
	        			<c:otherwise>
	        				<option value="${airplane.idSamolotu }">${airplane.nazwa }</option>
	        			</c:otherwise>
	        		</c:choose>
        		</c:forEach>
        	</select>
            <input type="hidden" value="updateAirplane" name="action"/>
            <input type="hidden" value="${idLsp }" name="idLsp"/>
        </p>
        <input type="submit" value="Zapisz">
    </fieldset>
    </form>

<jsp:include page="/WEB-INF/view-elements/footer.jsp" />