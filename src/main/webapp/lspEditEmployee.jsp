<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
<form action="<c:url value="/lsp"/>" class="block-content form" method="POST">       
        <fieldset class="grey-bg">
        <h1>Edycja pracownika w locie</h1>
        <legend>Wybierz pracownika</legend>
        <p>
        	<select name="idPracownika">
        	<c:set var="idPracownika" value="${idPracownika }"/>
        		<c:forEach var="employee" items="${employees }">
	        		<option <c:if test="${employee.idPracownika eq idPracownika}">selected="true"</c:if> value="${employee.idPracownika }">${employee.imie} ${ employee.nazwisko } </option>
        		</c:forEach>
        	</select>
            <input type="hidden" value="updateEmployee" name="action"/>
            <input type="hidden" value="${idLsp}" name="idLsp"/>
        </p>
        <input type="submit" value="Zapisz">
    </fieldset>
    </form>

<jsp:include page="/WEB-INF/view-elements/footer.jsp" />