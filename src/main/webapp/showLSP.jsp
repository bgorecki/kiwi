<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp" />

<table class="table">
	<thead>
		<tr>
			<th>ID lotu</th>
			<th>Lotnisko źródłowe</th>
			<th>Lotnisko docelowe</th>			
			<th>Samolot</th>
			<th>Pracownik</th>
			<th>Stanowisko</th>
			<th>Akcje</th>
		</tr>
	</thead>
	<c:forEach var="lsp" items="${lsp}">
		<tr>
			<td>${lsp.lotByIdLot.idLotu }</td>
			<td>${lsp.lotByIdLot.lotniskoByWylot.nazwa }</td>
			<td>${lsp.lotByIdLot.lotniskoByPrzylot.nazwa }</td>
			<td>${lsp.samolotByIdSam.nazwa }</td>
			<td>${lsp.pracownikByIdPrac.imie } ${lsp.pracownikByIdPrac.nazwisko }</td>
			<td>${lsp.pracownikByIdPrac.stanowisko }</td>
			<!-- The class table-actions is designed for action icons -->
			<td class="table-actions">
			<form action="<c:url value="/lsp"/>" method="post" style="float: left">
				<input type="hidden" name="action" value="editAirplane"/>
				<input type="hidden" name="idLsp" value="${lsp.idLsp }"/>
				<input type="hidden" name="idSamolotu" value="${lsp.samolotByIdSam.idSamolotu}"/>
				<input type="image" title="Edytuj samolot" class="with-tip" src="images/icons/fugue/pencil.png" width="16" height="16"/>
			</form>
			<form action="<c:url value="/lsp"/>" method="post" style="float: right">
				<input type="hidden" name="action" value="addLsp"/>
				<input type="hidden" name="idLsp" value="${lsp.idLsp}"/>
				<input type="image" title="Dodaj pracownika" class="with-tip" src="images/icons/fugue/plus-circle.png" width="16" height="16"/>
			</form>
			<form action="<c:url value="/lsp"/>" method="post" style="float: left">
				<input type="hidden" name="action" value="editEmployee"/>
				<input type="hidden" name="idLsp" value="${lsp.idLsp }"/>
				<input type="hidden" name="idPracownika" value="${lsp.pracownikByIdPrac.idPracownika}"/>
				<input type="image" title="Edytuj pracownika" class="with-tip" src="images/icons/fugue/user-detective.png" width="16" height="16"/>
			</form>
			<form action="<c:url value="/lsp"/>" method="post" style="float: right">
				<input type="hidden" name="action" value="delete"/>
				<input type="hidden" name="idLSP" value="${lsp.idLsp}"/>
				<input type="image" title="Usuń rekord" class="with-tip" src="images/icons/fugue/cross-circle.png" width="16" height="16"/>
			</form>
			</td>
		</tr>
	</c:forEach>
</table>


<jsp:include page="/WEB-INF/view-elements/footer.jsp" />