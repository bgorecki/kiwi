<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<title>Constellation Admin Skin</title>
	<meta charset="utf-8">
	
	<!-- Mobile metas -->
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
	
	<!-- Global stylesheets -->
	<link href='<c:url value="/css/reset.css" />' rel="stylesheet" type="text/css">
	<link href='<c:url value="/css/common.css" />' rel="stylesheet" type="text/css">
	<link href='<c:url value="/css/form.css" />' rel="stylesheet" type="text/css">
	<link href='<c:url value="/css/standard.css" />' rel="stylesheet" type="text/css">
	<link href='<c:url value="/css/special-pages.css" />' rel="stylesheet" type="text/css">
	
	<!-- Favicon -->
	<link rel="shortcut icon" type="image/x-icon" href='<c:url value="/favicon.ico"/>' >
	<link rel="icon" type="image/png" href="favicon-large.png">
	
	<!-- Generic libs -->
	<script type="text/javascript" src='<c:url value="/js/html5.js"/>' ></script><!-- this has to be loaded before anything else -->
	<script type="text/javascript" src='<c:url value="/js/jquery-1.4.2.min.js"/>' ></script>
	
	<!-- Template core functions -->
	<script type="text/javascript" src='<c:url value="/js/jquery.tip.js"/>' ></script>
	
</head>

<!-- the 'special-page' class is only an identifier for scripts -->
<body class="special-page code-page dark">
<!-- The template uses conditional comments to add wrappers div for ie8 and ie7 - just add .ie or .ie7 prefix to your css selectors when needed -->
<!--[if lt IE 9]><div class="ie"><![endif]-->
<!--[if lt IE 8]><div class="ie7"><![endif]-->