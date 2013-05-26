<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edycja lotniska</title>
    </head>
    <body>
        <form action="/kiwi/airportController" method="POST">
            3-literowy kod: <input type="text" name="code" value="${airport.code }"/></br>
            Państwo: <input type="text" name="country" value="${airport.country }"/></br>
            Miasto: <input type="text" name="city" value="${airport.city }"/></br>
            <input type="hidden" name="airportId" value="${airport.airportId}"/>
            <input type="hidden" value="update" name="action"/>
            <input type="submit" value="Zapisz">
        </form>
    </body>
</html>