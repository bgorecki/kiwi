<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Logowanie do panelu administracyjnego</title>
    <meta charset="utf-8">
    
    <!-- Global stylesheets -->
    <link href='<c:url value="/css/reset.css" />' rel="stylesheet" type="text/css">
    <link href='<c:url value="//css/common.css" rel="stylesheet" type="text/css">
    <link href='<c:url value="//css/form.css" rel="stylesheet" type="text/css">
    <link href='<c:url value="//css/standard.css" rel="stylesheet" type="text/css">
    <link href='<c:url value="//css/special-pages.css" rel="stylesheet" type="text/css">
    
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
    <link rel="icon" type="image/png" href="favicon-large.png">
    
    <!-- Generic libs -->
    <script type="text/javascript" src='<c:url value="//js/html5.js"></script><!-- this has to be loaded before anything else -->
    <script type="text/javascript" src='<c:url value="//js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src='<c:url value="//js/old-browsers.js"></script>       <!-- remove if you do not need older browsers detection -->
    
    <!-- Template core functions -->
    <script type="text/javascript" src='<c:url value="//js/common.js"></script>
    <script type="text/javascript" src='<c:url value="//js/standard.js"></script>
    <!--[if lte IE 8]><script type="text/javascript" src='<c:url value="//js/standard.ie.js"></script><![endif]-->
    <script type="text/javascript" src='<c:url value="//js/jquery.tip.js"></script>
    
</head>
<body class="special-page login-bg dark">
<!-- The template uses conditional comments to add wrappers div for ie8 and ie7 - just add .ie, .ie7 or .ie6 prefix to your css selectors when needed -->
<!--[if lt IE 9]><div class="ie"><![endif]-->
<!--[if lt IE 8]><div class="ie7"><![endif]-->

    <section id="login-block">
        <div class="block-border"><div class="block-content">
            
            <!--
            IE7 compatibility: if you want to remove the <h1>, 
            add style="zoom:1" to the above .block-content div
            -->
            <h1>Admin</h1>
            <div class="block-header">Zaloguj się</div>
            <c:if test="${not empty errorMsg}"> 
                <p class="message error no-margin">${errorMsg}</p>
            </c:if>
            
            <form class="form with-margin" name="login-form" id="login-form" method="post" action="login">
                <input type="hidden" name="a" id="a" value="send">
                <p class="inline-small-label">
                    <label for="login"><span class="big">Użytkownik</span></label>
                    <input type="text" name="login" id="login" class="full-width" value="${login}">
                </p>
                <p class="inline-small-label">
                    <label for="pass"><span class="big">Hasło</span></label>
                    <input type="password" name="password" id="pass" class="full-width" value="">
                </p>
                
                <button type="submit" class="float-right">Zaloguj</button>
            </form>
            
            <p>&nbsp;</p>&nbsp;
        </div></div>
    </section>

<!--[if lt IE 8]></div><![endif]-->
<!--[if lt IE 9]></div><![endif]-->
</body>
</html>