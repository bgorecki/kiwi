<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodawanie samolotu</title>
</head>
<body>
	<form action="/kiwi/airplaneController" method="POST">
    	Nazwa: <input type="text" name="name"/></br>
        Waga: <input type="text" name="weight"/></br>
        Rozpiętość skrzydeł: <input type="text" name="size"/></br>
        Zużycie paliwa/h: <input type="text" name="fuelConsumptionPerHour"/></br>
        <input type="hidden" value="add" name="action"/>
        <input type="hidden" value="" name="carrier"/>
        <input type="submit" value="Dodaj">
    </form>
</body>
</html>