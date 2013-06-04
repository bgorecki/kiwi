<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />
<table class="table" cellspacing="0">
	<thead>
		<tr>
			<th>Państwo</th>
			<th>Miasto</th>
			<th>Nazwa</th>
			<th>Akcje</th>
		</tr>
	</thead>
	<c:forEach var="airport" items="${airports}">
		<tr>
			<td>${airport.panstwo }</td>
			<td>${airport.miasto }</td>
			<td>${airport.nazwa }</td>
			<!-- The class table-actions is designed for action icons -->
			<td class="table-actions">
			<form action="<c:url value="/airports"/>" method="post" style="float: left">
				<input type="hidden" name="action" value="edit"/>
				<input type="hidden" name="idLotniska" value="${airport.idLotniska}"/>
				<input type="image" title="Edytuj lotnisko" class="with-tip" src="images/icons/fugue/pencil.png" width="16" height="16"/>
			</form>
			<form action="<c:url value="/airports"/>" method="post" style="float: right">
				<input type="hidden" name="action" value="delete"/>
				<input type="hidden" name="idLotniska" value="${airport.idLotniska}"/>
				<input type="image" title="Usuń lotnisko" class="with-tip" src="images/icons/fugue/cross-circle.png" width="16" height="16"/>
			</form>
			</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />