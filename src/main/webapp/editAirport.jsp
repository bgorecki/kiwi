<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view-elements/header.jsp"/>
<article class="container_12">
    <section class="grid_12">
        <div class="block-border">
            <div class="block-content">
                <form action="/kiwi/airports" method="POST" class="form">
                    Nazwa: <input type="text" name="nazwa" value="${airport.nazwa }"/></br>
                    Państwo: <input type="text" name="panstwo" value="${airport.panstwo }"/></br>
                    Miasto: <input type="text" name="miasto" value="${airport.miasto }"/></br>
                    <input type="hidden" name="idLotniska" value="${airport.idLotniska}"/>
                    <input type="hidden" value="update" name="action"/>
                    <input type="submit" value="Zapisz">
                </form>
            </div>
        </div>
    </section>
</article>
<jsp:include page="/WEB-INF/view-elements/footer.jsp"/>