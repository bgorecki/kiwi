<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
<table class="table" width="100%" cellspacing="0">
	<thead>
		<tr>
			<th>Nazwa</th>
			<th>Wielkość (rozpiętość skrzydeł) [m]</th>
			<th>Waga [kg]</th>
			<th>Zużycie paliwa [l/h]</th>
			<th>Akcje</th>
		</tr>
	</thead>
	<c:forEach var="airplane" items="${airplanes}">
		<tr>
			<td>${airplane.nazwa }</td>
			<td>${airplane.wielkosc }</td>
			<td>${airplane.waga }</td>
			<td>${airplane.zuzyciePaliwa }</td>
			<!-- The class table-actions is designed for action icons -->
			<td class="table-actions">
			<form action="<c:url value="/airplanes"/>" method="post" style="float: left">
				<input type="hidden" name="action" value="edit"/>
				<input type="hidden" name="idSamolotu" value="${airplane.idSamolotu}"/>
				<input type="image" title="Edytuj samolot" class="with-tip" src="images/icons/fugue/pencil.png" width="16" height="16"/>
			</form>
			<form action="<c:url value="/airplanes"/>" method="post" style="float: right">
				<input type="hidden" name="action" value="delete"/>
				<input type="hidden" name="idSamolotu" value="${airplane.idSamolotu}"/>
				<input type="image" title="Usuń samolot" class="with-tip" src="images/icons/fugue/cross-circle.png" width="16" height="16"/>
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />