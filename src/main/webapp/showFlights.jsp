<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />

<table class="table" cellspacing="0" width="100%">

    <thead>
    <tr>
        <!-- This is a special cell for loading statuses - see below for more -->
        <th class="black-cell"><span class="success"></span></th>

        <th scope="col">Wylot</th>
        <th scope="col">Godzina wylotu</th>
        <th scope="col">Przylot</th>
        <th scope="col">Godzina przylotu</th>
        <th scope="col">Dzień tygodnia</th>
        <th scope="col">Czas trwania [min]</th>
        <th scope="col">Przewoźnik</th>
        <th scope="col">Cena</th>
        <th scope="col" class="table-actions">Akcje</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <td colspan="10"><img src="images/icons/fugue/arrow-curve-000-left.png" width="16" height="16" class="picto"> <b>Znalezione rekordy:</b> ${ empty flights ? 0 : flights.size()} </td>
    </tr>
    </tfoot>

    <tbody>

    <c:forEach var="flights" items="${flights}" varStatus="status">
        <tr>
            <th scope="row" class="table-check-cell"></th>
            <td>${flights.lotniskoByWylot.miasto}-${flights.lotniskoByWylot.nazwa}</td>
            <td>${flights.godzinaWylotu}</td>
            <td>${flights.lotniskoByPrzylot.miasto}-${flights.lotniskoByPrzylot.nazwa}</td>
            <td>${flights.godzinaPrzylotu}</td>
            <c:if test="${flights.dzienTygodnia == 1}">
                <td>poniedziałek</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 2}">
                <td>wtorek</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 3}">
                <td>środa</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 4}">
                <td>czwartek</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 5}">
                <td>piątek</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 6}">
                <td>sobota</td>
            </c:if>
            <c:if test="${flights.dzienTygodnia == 7}">
                <td>niedziela</td>
            </c:if>

            <td>${flights.czasPodrozy}</td>
            <td>${flights.przewoznikByIdPrzew.nazwa}</td>
            <td>${flights.cenaStatyczna}</td>


            <!-- The class table-actions is designed for action icons -->

            <td class="table-actions">
                <a href="<c:url value="/flights?action=editFlightsy&id=${flights.idLotu}"/>" title="Edytuj" class="with-tip"><img src="<c:url value="/images/icons/fugue/pencil.png"/>" width="16" height="16"></a>
                <a href="<c:url value="/flights?action=deleteFlights&id=${flights.idLotu}"/>" title="Usuń" class="with-tip"><img src="images/icons/fugue/cross-circle.png" width="16" height="16"></a>
                <a href="<c:url value="/rentownosc.html?id=${flights.idLotu}"/>" title="Rentowność" class="with-tip"><img src="images/icons/fugue/chart.png" width="16" height="16"></a>
            </td>

        </tr>
    </c:forEach>

    </tbody>

</table>

<jsp:include page="/WEB-INF/view-elements/footer.jsp" />