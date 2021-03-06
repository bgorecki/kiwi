<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp"/>
<article class="container_12">
    <section class="grid_12">
        <div class="block-border">
            <div class="block-content">
                <form action="/kiwi/airplanes" method="POST" class="form">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="idSamolotu" value="${airplane.idSamolotu}"/>
                    Nazwa: <input type="text" name="nazwa" value="${airplane.nazwa }"/></br>
                    Wielkość (rozpiętość skrzydeł) [m]: <input type="text" name="wielkosc"
                                                               value="${airplane.wielkosc }"/></br>
                    Waga [kg]: <input type="text" name="waga" value="${airplane.waga }"/></br>
                    Zużycie paliwa [l/h]: <input type="text" name="zuzyciePaliwa"
                                                 value="${airplane.zuzyciePaliwa }"/></br>
                    <input type="submit" value="Zapisz zmiany"/>
                </form>
            </div>
        </div>
    </section>
</article>
<jsp:include page="/WEB-INF/view-elements/footer.jsp"/>