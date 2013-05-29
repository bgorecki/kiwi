<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/errorPage.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dodawanie lotniska</title>
    </head>
    <body>
        <form action="/kiwi/airportController" method="POST">
            3-literowy kod: <input type="text" name="code"/></br>
            Państwo: <input type="text" name="country"/></br>
            Miasto: <input type="text" name="city"/></br>
            <input type="hidden" value="add" name="action"/>
            <input type="submit" value="Dodaj">
        </form>
        
    
    </body>
</html>
