<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
    <jsp:include page="/WEB-INF/view-elements/header.jsp" />
        <form action="/kiwi/airportController" method="POST">
            Nazwa: <input type="text" name="nazwa"/></br>
            Państwo: <input type="text" name="panstwo"/></br>
            Miasto: <input type="text" name="miasto"/></br>
            <input type="hidden" value="add" name="action"/>
            <input type="submit" value="Dodaj">
        </form>
    <jsp:include page="/WEB-INF/view-elements/footer.jsp" />
