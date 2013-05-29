<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lotniska</title>
</head>
<body>
<table border="1">
<tr>
	<td>Państwo</td>
	<td>Miasto</td>
	<td>3-literowy kod</td>
	<td>Akcje</td>
</tr>
<tr>
<td>Polska</td><td>Krzeszowice</td><td>Test</td><td>Usuń</td>
</tr>
<c:forEach var="airport" items="${airports}">
	<tr>
		<td>${airport.country }</td>
		<td>${airport.city }</td>
		<td>${airport.code }</td>
		<!-- The class table-actions is designed for action icons -->
		<td class="table-actions">
		<form action="/kiwi/airportController" method="post" style="float: left">
			<input type="hidden" name="action" value="edit"/>
			<input type="hidden" name="code" value="${airport.code }"/>
			<input type="hidden" name="country" value="${airport.country }"/>
			<input type="hidden" name="city" value="${airport.city }"/>
			<input type="hidden" name="airportId" value="${airport.airportId}"/>
			<input type="image" title="Edytuj lotnisko" class="with-tip" src="images/icons/fugue/pencil.png" width="16" height="16"/>
		</form>
		<form action="/kiwi/airportController" method="post" style="float: right">
			<input type="hidden" name="action" value="delete"/>
			<input type="hidden" name="code" value="${airport.code }"/>
			<input type="hidden" name="country" value="${airport.country }"/>
			<input type="hidden" name="city" value="${airport.city }"/>
			<input type="hidden" name="airportId" value="${airport.airportId}"/>
			<input type="image" title="Usuń lotnisko" class="with-tip" src="images/icons/fugue/cross-circle.png" width="16" height="16"/>
		</form>
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>