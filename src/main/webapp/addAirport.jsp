<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
        <form action="<c:url value="/airports"/>" method="POST" class="form">
        <fieldset class="grey-bg">
        <legend>Dodawanie lotniska</legend>
        <p>
        	<label for="d" class="required">(pola wymagane)</label>
	    	<label for="nazwa" class="required">Nazwa: </label>
        	<input type="text" name="nazwa"/></br>
        	<label for="panstwo" class="required">Państwo: </label>
            <input type="text" name="panstwo"/></br>
            <label for="miasto" class="required">Miasto: </label>
            <input type="text" name="miasto"/></br>
            <input type="hidden" value="add" name="action"/>
        </p>
        <input type="submit" value="Dodaj">
    </fieldset>
        </form>
    <jsp:include page="/WEB-INF/view-elements/footer.jsp" />
