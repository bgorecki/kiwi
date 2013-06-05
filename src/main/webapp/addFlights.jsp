<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp"/>
<article class="container_12">
    <section class="grid_12">
        <div class="block-border">
            <div class="block-content">
                    <h1>Zarządzanie lotniskami</h1>
                <form action="<c:url value="/FlightController?action=editorsave&id=${param.id}"/>" class="form" method="post">

                    <fieldset class="grey-bg required">
                        <legend>Dane wymagane</legend>
                        <p class="grid_6">
                            <label for="cenaStatyczna">Cena początkowa</label>
                            <c:if test="${not empty errors.cenaStatyczna}"><span class="error relative full-width"></c:if>
                            <input type="text" name="cenaStatyczna" id="cenaStatyczna" value="${lot.cenaStatyczna}" class="full-width">
                            <c:if test="${not empty errors.cenaStatyczna}"><span class="check-error"></span></span></c:if>
                        </p>
                        <p class="grid_6">
                            <label for="czasPodrozy">Czas podróży</label>
                            <c:if test="${not empty errors.czasPodrozy}"><span class="error relative full-width"></c:if>
                            <input type="text" name="czasPodrozy" id="czasPodrozy" value="${lot.czasPodrozy}" class="full-width">
                            <c:if test="${not empty errors.czasPodrozy}"><span class="check-error"></span></span></c:if>
                        </p>

                     <p class="grid_4">
                        <label for="wylot">Lotnisko startowe</label>
                        <c:if test="${not empty errors.wylot}">
                                <span class="input-type-text error relative">
                            </c:if>
                                <select name="wylot" id="wylot" class="full-width">
                                    <c:forEach var="i" items="${lotniska}">
                                        <option value="${i.idLotniska}" <c:if test="${lot.lotniskoByWylot.idLotniska == i.idLotniska}">selected="selected"</c:if>>${i.nazwa} ${i.miasto}</option>
                                    </c:forEach>
                                </select>
                            <c:if test="${not empty errors.wylot}">
                                <span class="check-error"></span></span>
                        </c:if>
                    </p>
                        <p class="grid_4">
                            <label for="przylot">Lotnisko końcowe</label>
                            <c:if test="${not empty errors.przylot}">
                                <span class="input-type-text error relative">
                            </c:if>
                                <select name="przylot" id="przylot" class="full-width">
                                    <c:forEach var="i" items="${lotniska}">
                                        <option value="${i.idLotniska}" <c:if test="${lot.lotniskoByPrzylot.idLotniska == i.idLotniska}">selected="selected"</c:if>>${i.nazwa} ${i.miasto}</option>
                                    </c:forEach>
                                </select>
                            <c:if test="${not empty errors.przylot}">
                                <span class="check-error"></span></span>
                            </c:if>
                        </p>
                        <p class="grid_4">
                            <label for="dzienTygodnia">Dzień tygodnia</label>
                            <c:if test="${not empty errors.dzienTygodnia}">
                                <span class="input-type-text error relative">
                            </c:if>
                                <select name="dzienTygodnia" id="dzienTygodnia" class="full-width">
                                    <option value="1" <c:if test="${lot.dzienTygodnia == 1}">selected="selected"</c:if>>Poniedziałek</option>
                                    <option value="2" <c:if test="${lot.dzienTygodnia == 2}">selected="selected"</c:if>>Wtorek</option>
                                    <option value="3" <c:if test="${lot.dzienTygodnia == 3}">selected="selected"</c:if>>Środa</option>
                                    <option value="4" <c:if test="${lot.dzienTygodnia == 4}">selected="selected"</c:if>>Czwartek</option>
                                    <option value="5" <c:if test="${lot.dzienTygodnia == 5}">selected="selected"</c:if>>Piątek</option>
                                    <option value="6" <c:if test="${lot.dzienTygodnia == 6}">selected="selected"</c:if>>Sobota</option>
                                    <option value="7" <c:if test="${lot.dzienTygodnia == 7}">selected="selected"</c:if>>Niedziela</option>
                                </select>
                            <c:if test="${not empty errors.dzienTygodnia}">
                                <span class="check-error"></span></span>
                            </c:if>
                        </p>
                        <p class="grid_6">
                            <label for="godzinaPrzylotu">Godzina przylotu</label>
                            <c:if test="${not empty errors.godzinaPrzylotu}"><span class="error relative full-width"></c:if>
                            <input type="text" name="godzinaPrzylotu" id="godzinaPrzylotu" value="${lot.godzinaPrzylotu}" class="full-width">
                            <c:if test="${not empty errors.godzinaPrzylotu}"><span class="check-error"></span></span></c:if>
                        </p>

                        <p class="grid_6">
                            <label for="godzinaWylotu">Godzina wylotu</label>
                            <c:if test="${not empty errors.godzinaWylotu}"><span class="error relative full-width"></c:if>
                            <input type="text" name="godzinaWylotu" id="godzinaWylotu" value="${lot.godzinaWylotu}" class="full-width">
                            <c:if test="${not empty errors.godzinaWylotu}"><span class="check-error"></span></span></c:if>
                        </p>

                        <div class="float-right">
                            <button type="submit">Zapisz</button>
                        </div>

                        <div class="clearfix"></div>
                    </fieldset>
                </form>

            </div>
        </div>
    </section>
</article>


<jsp:include page="/WEB-INF/view-elements/footer.jsp"/>